package com.osomapps.pt.admin.program;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.ParseExercise;
import com.osomapps.pt.programs.ParseExerciseRepository;
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
import com.osomapps.pt.xlsx.XlsxProgramParser;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import com.osomapps.pt.programs.ParseGoalRepository;
import com.osomapps.pt.programs.ParsePart;
import com.osomapps.pt.programs.ParsePartRepository;
import com.osomapps.pt.programs.ParseRound;
import com.osomapps.pt.programs.ParseRoundRepository;
import com.osomapps.pt.programs.ParseSheets;
import com.osomapps.pt.programs.ParseUserGroupRepository;
import com.osomapps.pt.programs.ParseWarmupWorkoutItemRepository;
import com.osomapps.pt.programs.ParseWorkoutItemSet;
import com.osomapps.pt.programs.ParseWorkoutItemSetRepository;
import com.osomapps.pt.xlsx.ExcelSheets;
import java.io.IOException;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

@Service
class AdminProgramService {

    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;
    private final ProgramRepository programRepository;
    private final ParseExerciseRepository parseExerciseRepository;
    private final ParseGoalRepository parseGoalRepository;
    private final ParseUserGroupRepository parseUserGroupRepository;
    private final ParseRoundRepository parseRoundRepository;
    private final ParsePartRepository parsePartRepository;
    private final ParseWorkoutRepository parseWorkoutRepository;
    private final ParseWarmupWorkoutItemRepository parseWarmupWorkoutItemRepository;
    private final ParseWorkoutItemRepository parseWorkoutItemRepository;
    private final ParseWorkoutItemSetRepository parseWorkoutItemSetRepository;

    AdminProgramService(ProgramRepository programRepository,
            ParseExerciseRepository parseExerciseRepository,
            ParseGoalRepository parseGoalRepository,
            ParseUserGroupRepository parseUserGroupRepository,
            ParseRoundRepository parseRoundRepository,
            ParsePartRepository parsePartRepository,
            ParseWorkoutRepository parseWorkoutRepository,
            ParseWarmupWorkoutItemRepository parseWarmupWorkoutItemRepository,
            ParseWorkoutItemRepository parseWorkoutItemRepository,
            ParseWorkoutItemSetRepository parseWorkoutItemSetRepository) {
        this.programRepository = programRepository;
        this.parseExerciseRepository = parseExerciseRepository;
        this.parseGoalRepository = parseGoalRepository;
        this.parseUserGroupRepository = parseUserGroupRepository;
        this.parseRoundRepository = parseRoundRepository;
        this.parsePartRepository = parsePartRepository;
        this.parseWorkoutRepository = parseWorkoutRepository;
        this.parseWarmupWorkoutItemRepository = parseWarmupWorkoutItemRepository;
        this.parseWorkoutItemRepository = parseWorkoutItemRepository;
        this.parseWorkoutItemSetRepository = parseWorkoutItemSetRepository;
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
                .parseExercises(program.getParseExercises().stream().map(exercise -> ParseExerciseDTO.builder()
                        .exercise_id(exercise.getExercise_id())
                        .exercise_name(exercise.getExercise_name())
                        .user_group_1_percent(exercise.getUser_group_1_percent())
                        .user_group_2_percent(exercise.getUser_group_2_percent())
                        .user_group_3_percent(exercise.getUser_group_3_percent())
                        .user_group_4_percent(exercise.getUser_group_4_percent())
                        .basis_for_calculations(exercise.getBasis_for_calculations())
                        .build()
                ).collect(Collectors.toList()))
                .parseGoals(program.getParseGoals().stream().map(result -> ParseGoalDTO.builder()
                    .id(result.getId())
                    .name(result.getName())
                    .loops(result.getLoops())
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
                                .sets(workoutItem.getParseWorkoutItemSets().stream().map(set ->
                                    new ParseWorkoutItemSetDTO()
                                        .setRepetitions(set.getRepetitions())
                                        .setRepetitions_to_failure(set.getRepetitions_to_failure())
                                        .setWeight(set.getWeight())
                                        .setBodyweight(set.getBodyweight())
                                        .setTime_in_min(set.getTime_in_min())
                                        .setSpeed(set.getSpeed())
                                        .setIncline(set.getIncline())
                                        .setResistance(set.getResistance())
                                ).collect(Collectors.toList()))
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
        final ParseSheets parseSheets = parseDataUrlAndSaveGoals(programRequestDTO, savedProgram);
        program.setParseExercises(parseSheets.getParseExercises());
        program.setParseGoals(parseSheets.getParseGoals());
        return programToDto(program);
    }

    private ParseSheets parseDataUrlAndSaveGoals(ProgramRequestDTO programRequestDTO, final ParseProgram savedProgram) {
        final ParseSheets parseSheets = parseDataUrl(programRequestDTO, savedProgram);
        parseExerciseRepository.save(parseSheets.getParseExercises());
        final List<ParseGoal> savedParseGoals = parseGoalRepository.save(parseSheets.getParseGoals());
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
                                    parseWorkoutItem.getParseWorkoutItemSets().forEach((parseWorkoutItemSet) -> {
                                        parseWorkoutItemSet.setParseWorkoutItem(parseWorkoutItem);
                                    });
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
                            parseWorkout.getParseWorkoutItems().forEach(parseWorkoutItem -> {
                                 parseWorkoutItemSetRepository.save(parseWorkoutItem.getParseWorkoutItemSets());
                            });
                        });
                    });
                });
            });
        });
        return parseSheets;
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
        parseExerciseRepository.delete(program.getParseExercises());
        parseGoalRepository.delete(program.getParseGoals());
        final ParseSheets parseSheets = parseDataUrlAndSaveGoals(programRequestDTO, program);
        program.setParseExercises(parseSheets.getParseExercises());
        program.setParseGoals(parseSheets.getParseGoals());
        return programToDto(programRepository.save(program));
    }

    private ParseSheets parseDataUrl(ProgramRequestDTO programRequestDTO, final ParseProgram program) {
        final ByteArrayInputStream arrayInputStream = dataUrlToInputStream(programRequestDTO.getDataUrl());
        final XlsxProgramParser xlsxProgramParser = XlsxProgramParser.of(arrayInputStream);
        final ExcelSheets excelSheets = xlsxProgramParser.getExcelSheets();
        return new ParseSheets()
                .setParseExercises(excelSheets.getExcelExercises().stream().map(exercise ->
                  new ParseExercise()
                        .setExercise_id(exercise.getExercise_id())
                        .setExercise_name(exercise.getExercise_name())
                        .setUser_group_1_percent(exercise.getUser_group_1_percent())
                        .setUser_group_2_percent(exercise.getUser_group_2_percent())
                        .setUser_group_3_percent(exercise.getUser_group_3_percent())
                        .setUser_group_4_percent(exercise.getUser_group_4_percent())
                        .setBasis_for_calculations(exercise.getBasis_for_calculations())
                        .setParseProgram(program)
                ).collect(Collectors.toList()))
                .setParseGoals(excelSheets.getExcelGoals().stream().map(goal ->
            new ParseGoal()
                .setErrors(StringUtils.abbreviate(goal.getErrors().stream().collect(Collectors.joining(", ")), 1000))
                .setName(goal.getName())
                .setSheet_index(goal.getSheetIndex())
                .setLoops(goal.getLoops())
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
                ).collect(Collectors.toList()))
        ).collect(Collectors.toList()));
    }

    private ParseWorkout createParseWorkout(Workout workout) {
        return new ParseWorkout()
                .setName(workout.getName())
                .setParseWarmupWorkoutItems(workout.getWarmup() == null ? Collections.emptyList()
                        : Arrays.asList(new ParseWarmupWorkoutItem()
                                .setName(workout.getWarmup().getExercise())
                                .setExercise_id(workout.getWarmup().getExerciseId())
                                .setSpeed(workout.getWarmup().getSpeed())
                                .setIncline(workout.getWarmup().getIncline())
                                .setTime_in_min(workout.getWarmup().getTimeInMin())))
                .setParseWorkoutItems(workout.getWorkoutItems().stream().map(workoutItem ->
                        new ParseWorkoutItem()
                                .setColumn_index(workoutItem.getColumnIndex())
                                .setRow_index(workoutItem.getRowIndex())
                                .setName(workoutItem.getInput().getExercise())
                                .setExercise_id(workoutItem.getExerciseId())
                                .setParseWorkoutItemSets(workoutItem.getInput().getSets().stream().map(set ->
                                        new ParseWorkoutItemSet()
                                            .setRepetitions(set.getRepetitions())
                                            .setRepetitions_to_failure(BooleanUtils.isTrue(set.getRepetitionsToFailure()))
                                            .setWeight(set.getWeight())
                                            .setBodyweight(BooleanUtils.isTrue(set.getBodyweight()))
                                            .setTime_in_min(set.getTimeInMin())
                                            .setSpeed(set.getSpeed())
                                            .setIncline(set.getIncline())
                                            .setResistance(set.getResistance())
                                ).collect(Collectors.toList()))
                ).collect(Collectors.toList()));
    }

    ByteArrayInputStream dataUrlToInputStream(String dataUrl) {
        final String encodedString = dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH);
        return new ByteArrayInputStream(Base64.getDecoder().decode(encodedString));
    }

    void dataUrlToOutputStream(String dataUrl, OutputStream outputStream) {
        final String encodedString = dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH);
        try {
            outputStream.write(Base64.getDecoder().decode(encodedString));
        } catch (IOException | IllegalArgumentException ex) {
        }
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
}
