package com.osomapps.pt.admin.program;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.ParseGoal;
import com.osomapps.pt.programs.ParseUserGroup;
import com.osomapps.pt.programs.ParseProgram;
import com.osomapps.pt.programs.ProgramRepository;
import com.osomapps.pt.xlsx.Workout;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.osomapps.pt.programs.ParseWarmupWorkoutItem;
import com.osomapps.pt.programs.ParseWorkout;
import com.osomapps.pt.programs.ParseWorkoutItem;
import com.osomapps.pt.programs.ParseWorkoutItemRepository;
import com.osomapps.pt.programs.ParseWorkoutRepository;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import com.osomapps.pt.xlsx.ExcelGoal;
import com.osomapps.pt.xlsx.XlsxModifier;
import com.osomapps.pt.xlsx.XlsxProgramParser;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import com.osomapps.pt.programs.ParseGoalRepository;
import com.osomapps.pt.programs.ParsePart;
import com.osomapps.pt.programs.ParsePartRepository;
import com.osomapps.pt.programs.ParseRound;
import com.osomapps.pt.programs.ParseRoundRepository;
import com.osomapps.pt.programs.ParseUserGroupRepository;
import com.osomapps.pt.programs.ParseWarmupWorkoutItemRepository;
import com.osomapps.pt.xlsx.Round;
import com.osomapps.pt.xlsx.UserGroup;
import org.apache.commons.lang3.BooleanUtils;

@Service
class AdminProgramService {
    
    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;
    private final ProgramRepository programRepository;
    private final ParseGoalRepository parseGoalRepository;
    private final ParseUserGroupRepository parseUserGroupRepository;
    private final ParseRoundRepository parseRoundRepository;
    private final ParsePartRepository parsePartRepository;
    private final ParseWorkoutRepository parseWorkoutRepository;
    private final ParseWarmupWorkoutItemRepository parseWarmupWorkoutItemRepository;
    private final ParseWorkoutItemRepository parseWorkoutItemRepository;
    private final InWorkoutItemRepository inWorkoutItemRepository;
    private final AdminProgramAssignService adminProgramAssignService;

    AdminProgramService(ProgramRepository programRepository,
            ParseGoalRepository parseGoalRepository,
            ParseUserGroupRepository parseUserGroupRepository,
            ParseRoundRepository parseRoundRepository,
            ParsePartRepository parsePartRepository,
            ParseWorkoutRepository parseWorkoutRepository,
            ParseWarmupWorkoutItemRepository parseWarmupWorkoutItemRepository,
            ParseWorkoutItemRepository parseWorkoutItemRepository,
            InWorkoutItemRepository inWorkoutItemRepository,
            AdminProgramAssignService adminProgramAssignService) {
        this.programRepository = programRepository;
        this.parseGoalRepository = parseGoalRepository;
        this.parseUserGroupRepository = parseUserGroupRepository;
        this.parseRoundRepository = parseRoundRepository;
        this.parsePartRepository = parsePartRepository;
        this.parseWorkoutRepository = parseWorkoutRepository;
        this.parseWarmupWorkoutItemRepository = parseWarmupWorkoutItemRepository;
        this.parseWorkoutItemRepository = parseWorkoutItemRepository;
        this.inWorkoutItemRepository = inWorkoutItemRepository;
        this.adminProgramAssignService = adminProgramAssignService;
    }

    List<ProgramResponseDTO> findAll() {
        return programRepository.findAll(sortByIdAsc()).stream().map(program -> programToDto(program))
        .collect(Collectors.toList());
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    private ProgramResponseDTO programToDto(ParseProgram program) {
        return ProgramResponseDTO.builder()
                .id(program.getId())
                .name(program.getName())
                .fileName(program.getFile_name())
                .fileSize(program.getFile_size())
                .fileType(program.getFile_type())
                .dataUrl(program.getData_url())
                .updated(program.getUpdated())
                .parseGoals(program.getParseGoals().stream().map(result -> ParseGoalDTO.builder()
                    .id(result.getId())
                    .name(result.getName())
                    .errors(result.getErrors())
                    .userGroups(result.getParseUserGroups().stream().map(userGroup -> ParseUserGroupDTO.builder()
                        .id(userGroup.getId())
                        .name(userGroup.getName())
                        .rounds(userGroup.getParseRounds() == null ? null : userGroup.getParseRounds().stream().map(round -> ParseRoundDTO.builder()
                                .id(round.getId())
                                .name(round.getName())
                                .parts(round.getParseParts().stream().map(part -> ParsePartDTO.builder()
                                        .id(part.getId())
                                        .name(part.getName())
                                        .workouts(part.getParseWorkouts().stream().map(workout ->
                                                createParseWorkoutDTO(workout)).collect(Collectors.toList()))
                                        .build()).collect(Collectors.toList()))
                                .build()).collect(Collectors.toList()))
                            .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList()))
                .build();
    }

    private ParseWorkoutDTO createParseWorkoutDTO(ParseWorkout workout) {
        return ParseWorkoutDTO.builder()
                .id(workout.getId())
                .name(workout.getName())
                .warmupWorkoutItem(workout.getParseWarmupWorkoutItems() == null ? null
                        : ParseWarmupWorkoutItemDTO.builder()
                                .id(workout.getParseWarmupWorkoutItems().get(0).getId())
                                .name(workout.getParseWarmupWorkoutItems().get(0).getName())
                                .speed(workout.getParseWarmupWorkoutItems().get(0).getSpeed())
                                .incline(workout.getParseWarmupWorkoutItems().get(0).getIncline())
                                .time_in_min(workout.getParseWarmupWorkoutItems().get(0).getTime_in_min())
                                .build())
                .workoutItems(workout.getParseWorkoutItems() == null ? null : workout.getParseWorkoutItems().stream().map(workoutItem ->
                        ParseWorkoutItemDTO.builder()
                                .id(workoutItem.getId())
                                .name(workoutItem.getName())
                                .sets(workoutItem.getSets())
                                .repetitions(workoutItem.getRepetitions())
                                .weight(workoutItem.getWeight())
                                .bodyweight(workoutItem.getBodyweight())
                                .time_in_min(workoutItem.getTime_in_min())
                                .speed(workoutItem.getSpeed())
                                .resistance(workoutItem.getResistance())
                                .build()).collect(Collectors.toList()))
                .build();
    }

    ProgramResponseDTO findOne(Long id) {
        final ParseProgram program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        return programToDto(program);
    }

    ProgramResponseDTO create(ProgramRequestDTO programRequestDTO) {
        final ParseProgram program = new ParseProgram();
        program.setName(programRequestDTO.getName());
        program.setFile_name(programRequestDTO.getFileName());
        program.setFile_size(programRequestDTO.getFileSize());
        program.setFile_type(programRequestDTO.getFileType());
        program.setData_url(programRequestDTO.getDataUrl());
        final ParseProgram savedProgram = programRepository.save(program);
        program.setParseGoals(adminProgramAssignService.assign(parseDataUrlAndSaveGoals(programRequestDTO, savedProgram)));
        return programToDto(program);
    }

    private List<ParseGoal> parseDataUrlAndSaveGoals(ProgramRequestDTO programRequestDTO, final ParseProgram savedProgram) {
        final List<ParseGoal> savedParseGoals = parseGoalRepository.save(parseDataUrl(programRequestDTO, savedProgram));
        savedParseGoals.forEach((parseGoal) -> {
            parseGoal.getParseUserGroups().forEach((parseUserGroup) -> {
                parseUserGroup.setParseGoal(parseGoal);
                parseUserGroup.getParseRounds().forEach((parseRound) -> {
                    parseRound.setParseUserGroup(parseUserGroup);
                    parseRound.getParseParts().forEach((parsePart) -> {
                        parsePart.setParseRound(parseRound);
                        parsePart.getParseWorkouts().forEach((parseWorkout) -> {
                            parseWorkout.setParsePart(parsePart);
                            if (parseWorkout.getParseWarmupWorkoutItems() != null) {
                                parseWorkout.getParseWarmupWorkoutItems().forEach((parseWarmupWorkoutItem) -> {
                                    parseWarmupWorkoutItem.setParseWorkout(parseWorkout);
                                });
                            }
                            if (parseWorkout.getParseWorkoutItems() != null) {
                                parseWorkout.getParseWorkoutItems().forEach((parseWorkoutItem) -> {
                                    parseWorkoutItem.setParseWorkout(parseWorkout);
                                });
                            }
                        });
                    });
                });
            });
        });
        savedParseGoals.forEach((parseGoal) -> {
            parseUserGroupRepository.save(parseGoal.getParseUserGroups());
            parseGoal.getParseUserGroups().forEach((parseUserGroup) -> {
                parseRoundRepository.save(parseUserGroup.getParseRounds());
                parseUserGroup.getParseRounds().forEach((parseRound) -> {
                    parsePartRepository.save(parseRound.getParseParts());
                    parseRound.getParseParts().forEach((parsePart) -> {
                        parseWorkoutRepository.save(parsePart.getParseWorkouts());
                        parsePart.getParseWorkouts().forEach((parseWorkout) -> {
                            parseWarmupWorkoutItemRepository.save(parseWorkout.getParseWarmupWorkoutItems());
                            parseWorkoutItemRepository.save(parseWorkout.getParseWorkoutItems());
                        });
                    });
                });
            });
        });
        return savedParseGoals;
    }

    ProgramResponseDTO update(Long id, ProgramRequestDTO programRequestDTO) {
        final ParseProgram program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        program.setName(programRequestDTO.getName());
        program.setFile_name(programRequestDTO.getFileName());
        program.setFile_size(programRequestDTO.getFileSize());
        program.setFile_type(programRequestDTO.getFileType());
        program.setData_url(programRequestDTO.getDataUrl());
        program.setUpdated(LocalDateTime.now());
        parseGoalRepository.delete(program.getParseGoals());
        program.setParseGoals(adminProgramAssignService.assign(parseDataUrlAndSaveGoals(programRequestDTO, program)));
        return programToDto(programRepository.save(program));
    }

    private List<ParseGoal> parseDataUrl(ProgramRequestDTO programRequestDTO, final ParseProgram program) {
        final ByteArrayInputStream arrayInputStream = dataUrlToInputStream(programRequestDTO.getDataUrl());
        final XlsxProgramParser xlsxProgramParser = XlsxProgramParser.of(arrayInputStream);
        final List<ExcelGoal> excelGoals = xlsxProgramParser.getExcelGoals();
        return excelGoals.stream().map(goal -> {
            final ParseGoal parseGoal = new ParseGoal()
                .setName(goal.getName())
                .setSheet_index(goal.getSheetIndex())
                .setParseProgram(program)
                .setParseUserGroups(goal.getUserGroups().stream().map(userGroup ->
                        new ParseUserGroup()
                            .setName(userGroup.getName())
                            .setParseRounds(userGroup.getRounds().stream().map(round ->
                                new ParseRound()
                                    .setName(round.getName())
                                    .setParseParts(round.getParts().stream().map(part ->
                                        new ParsePart()
                                            .setName(part.getName())
                                            .setParseWorkouts(part.getWorkouts().stream().map(workout ->
                                                createParseWorkout(workout)
                                            ).collect(Collectors.toList()))
                                    ).collect(Collectors.toList()))
                            ).collect(Collectors.toList()))
                ).collect(Collectors.toList()));
            parseGoal.setErrors(goal.getErrors().stream().collect(Collectors.joining(", ")));
            return parseGoal;
        }).collect(Collectors.toList());
    }

    private ParseWorkout createParseWorkout(Workout workout) {
        return new ParseWorkout()
                .setName(workout.getName())
                .setParseWarmupWorkoutItems(workout.getWarmup() == null ? Collections.emptyList()
                        : Arrays.asList(new ParseWarmupWorkoutItem()
                                .setName(workout.getWarmup().getExercise())
                                .setSpeed(workout.getWarmup().getSpeed())
                                .setIncline(workout.getWarmup().getIncline())
                                .setTime_in_min(workout.getWarmup().getTimeInMin())))
                .setParseWorkoutItems(workout.getWorkoutItems().stream().map(workoutItem ->
                        new ParseWorkoutItem()
                                .setColumn_index(workoutItem.getColumnIndex())
                                .setRow_index(workoutItem.getRowIndex())
                                .setName(workoutItem.getInput().getExercise())
                                .setSets(workoutItem.getInput().getSets())
                                .setRepetitions(workoutItem.getInput().getRepetitions())
                                .setWeight(workoutItem.getInput().getWeight())
                                .setBodyweight(BooleanUtils.isTrue(workoutItem.getInput().getBodyweight()))
                                .setTime_in_min(workoutItem.getInput().getTimeInMin())
                ).collect(Collectors.toList()));
    }

    ByteArrayInputStream dataUrlToInputStream(String dataUrl) {
        final String encodedString = dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH);
        return new ByteArrayInputStream(Base64.getDecoder().decode(encodedString));
    }

    ProgramResponseDTO delete(Long id) {
        final ParseProgram program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        final ProgramResponseDTO responseDTO = programToDto(program);
        programRepository.delete(program);
        return responseDTO;
    }

    ProgramResponseDTO createXlsx(Long programId, OutputStream outputStream) {
        final ParseProgram program = programRepository.findOne(programId);
        final ByteArrayInputStream inputStream = dataUrlToInputStream(program.getData_url());
        final XlsxModifier xlsxModifier = XlsxModifier.of(inputStream);
        final List<ParseGoal> parseGoals = program.getParseGoals();
        final List<ExcelGoal> excelGoals = parseGoals.stream().map(parseGoal ->
                new ExcelGoal()
                    .setSheetIndex(parseGoal.getSheet_index())
                    .setUserGroups(parseGoal.getParseUserGroups().stream().map(parseUserGroup ->
                        createUserGroup(parseUserGroup)
                    ).collect(Collectors.toList()))
        ).collect(Collectors.toList());
        xlsxModifier.updateCellData(outputStream, excelGoals);
        return programToDto(program);
    }

//    private WorkoutItem createWorkoutItem(ParseWorkoutItem parseWorkoutItem) {
//        InWorkoutItem inWorkoutItem =
//                parseWorkoutItem.getIn_workout_item_id() == null ? null
//                : inWorkoutItemRepository.findOne(parseWorkoutItem.getIn_workout_item_id());
//        WorkoutItem workoutItem = new WorkoutItem()
//                .setColumnIndex(parseWorkoutItem.getColumn_index())
//                .setRowIndex(parseWorkoutItem.getRow_index())
//                .setInput(new Input()
//                        .setSets(parseWorkoutItem.getSets())
//                        .setRepetitions(parseWorkoutItem.getRepetitions())
//                        .setWeight(parseWorkoutItem.getWeight()));
//        if (inWorkoutItem != null && !inWorkoutItem.getInWorkoutItemReports().isEmpty()) {
//            InWorkoutItemReport inWorkoutItemReport = inWorkoutItem.getInWorkoutItemReports()
//                    .get(inWorkoutItem.getInWorkoutItemReports().size() - 1);
//            workoutItem.setOutput(new Output()
//                    .setSets(inWorkoutItemReport.getInWorkoutItemSetReports().size())
//                    .setRepetitions(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
//                            .map(item -> item.getRepetitions()).collect(Collectors.toList()))
//                    .setWeights(inWorkoutItemReport.getInWorkoutItemSetReports().stream()
//                            .map(item -> item.getWeight() == null ? null : item.getWeight().intValue()).collect(Collectors.toList())));
//        }
//        return workoutItem;
//    }

    private UserGroup createUserGroup(ParseUserGroup parseUserGroup) {
        return new UserGroup()
                .setRounds(parseUserGroup.getParseRounds().stream().map(
                        parseRound -> createRound(parseRound)).collect(Collectors.toList()));
    }

    private Round createRound(ParseRound parseRound) {
        return new Round();
    }
}
