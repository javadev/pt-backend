package com.github.pt.admin.program;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.programs.ParseUser;
import com.github.pt.programs.Program;
import com.github.pt.programs.ProgramRepository;
import com.github.pt.xlsx.ExcelUser;
import com.github.pt.xlsx.XlsxParser;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.github.pt.programs.ParseUserRepository;
import com.github.pt.programs.ParseWorkout;
import com.github.pt.programs.ParseWorkoutItem;
import com.github.pt.programs.ParseWorkoutItemRepository;
import com.github.pt.programs.ParseWorkoutRepository;

@Service
class AdminProgramService {
    
    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;
    private final ProgramRepository programRepository;
    private final ParseUserRepository parseUserRepository;
    private final ParseWorkoutRepository parseWorkoutRepository;
    private final ParseWorkoutItemRepository parseWorkoutItemRepository;
    private final AdminProgramAssignService adminProgramAssignService;

    AdminProgramService(ProgramRepository programRepository,
            ParseUserRepository parseResultRepository,
            ParseWorkoutRepository parseWorkoutRepository,
            ParseWorkoutItemRepository parseWorkoutItemRepository,
            AdminProgramAssignService adminProgramAssignService) {
        this.programRepository = programRepository;
        this.parseUserRepository = parseResultRepository;
        this.parseWorkoutRepository = parseWorkoutRepository;
        this.parseWorkoutItemRepository = parseWorkoutItemRepository;
        this.adminProgramAssignService = adminProgramAssignService;
    }

    List<ProgramResponseDTO> findAll() {
        return programRepository.findAll(sortByIdAsc()).stream().map(program -> programToDto(program))
        .collect(Collectors.toList());
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    private ProgramResponseDTO programToDto(Program program) {
        return ProgramResponseDTO.builder()
                .id(program.getId())
                .name(program.getName())
                .fileName(program.getFile_name())
                .fileSize(program.getFile_size())
                .fileType(program.getFile_type())
                .dataUrl(program.getData_url())
                .updated(program.getUpdated())
                .parseUsers(program.getParseUsers().stream().map(result -> ParseUserDTO.builder()
                    .id(result.getId())
                    .name(result.getName())
                    .workouts(result.getParseWorkouts().stream().map(workout -> ParseWorkoutDTO.builder()
                        .id(workout.getId())
                        .name(workout.getName())
                        .workoutItems(workout.getParseWorkoutItems().stream().map(workoutItem -> ParseWorkoutItemDTO.builder()
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
                        .build()
                    ).collect(Collectors.toList()))
                    .errors(result.getErrors())
                    .build()
                ).collect(Collectors.toList()))
                .build();
    }

    ProgramResponseDTO findOne(Long id) {
        final Program program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        return programToDto(program);
    }

    ProgramResponseDTO create(ProgramRequestDTO programRequestDTO) {
        final Program program = new Program();
        program.setName(programRequestDTO.getName());
        program.setFile_name(programRequestDTO.getFileName());
        program.setFile_size(programRequestDTO.getFileSize());
        program.setFile_type(programRequestDTO.getFileType());
        program.setData_url(programRequestDTO.getDataUrl());
        final Program savedProgram = programRepository.save(program);
        List<ParseUser> savedParseUsers = parseDataUrlAndSaveUsers(programRequestDTO, savedProgram);
        program.setParseUsers(savedParseUsers);
        return programToDto(program);
    }

    private List<ParseUser> parseDataUrlAndSaveUsers(ProgramRequestDTO programRequestDTO, final Program savedProgram) {
        final List<ParseUser> savedParseUsers = parseUserRepository.save(parseDataUrl(programRequestDTO, savedProgram));
        for (final ParseUser parseUser : savedParseUsers) {
            for (final ParseWorkout parseWorkout : parseUser.getParseWorkouts()) {
                parseWorkout.setParseUser(parseUser);
            }
            final List<ParseWorkout> savedParseWorkouts = parseWorkoutRepository.save(parseUser.getParseWorkouts());
            parseUser.setParseWorkouts(savedParseWorkouts);
            for (final ParseWorkout parseWorkout : savedParseWorkouts) {
                for (final ParseWorkoutItem parseWorkoutItem : parseWorkout.getParseWorkoutItems()) {
                    parseWorkoutItem.setParseWorkout(parseWorkout);
                }
                final List<ParseWorkoutItem> savedParseWorkoutItems = parseWorkoutItemRepository.save(parseWorkout.getParseWorkoutItems());
                parseWorkout.setParseWorkoutItems(savedParseWorkoutItems);
            }
        }
        return savedParseUsers;
    }

    ProgramResponseDTO update(Long id, ProgramRequestDTO programRequestDTO) {
        final Program program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        program.setName(programRequestDTO.getName());
        program.setFile_name(programRequestDTO.getFileName());
        program.setFile_size(programRequestDTO.getFileSize());
        program.setFile_type(programRequestDTO.getFileType());
        program.setData_url(programRequestDTO.getDataUrl());
        program.setUpdated(LocalDateTime.now());
        parseUserRepository.delete(program.getParseUsers());
        program.setParseUsers(adminProgramAssignService.assign(parseDataUrlAndSaveUsers(programRequestDTO, program)));
        return programToDto(programRepository.save(program));
    }
    
    private List<ParseUser> parseDataUrl(ProgramRequestDTO programRequestDTO, final Program program) {
        final ByteArrayInputStream arrayInputStream = dataUrlToInputStream(programRequestDTO.getDataUrl());
        final XlsxParser xlsxParser = XlsxParser.of(arrayInputStream);
        final List<ExcelUser> excelUsers = xlsxParser.getExcelUsers();
        return excelUsers.stream().map(user -> {
            final ParseUser parseUser = new ParseUser();
            parseUser.setName(user.getName());
            parseUser.setSheet_index(user.getSheetIndex());
            parseUser.setParseWorkouts(user.getWorkouts().stream()
                    .map(workout -> {
                        ParseWorkout parseWorkout = new ParseWorkout();
                        parseWorkout.setParseUser(parseUser);
                        parseWorkout.setName(workout.getName());
                        parseWorkout.setParseWorkoutItems(workout.getWorkoutItems().stream().map(workoutItem -> {
                            ParseWorkoutItem parseWorkoutItem = new ParseWorkoutItem();
                            parseWorkoutItem.setParseWorkout(parseWorkout);
                            parseWorkoutItem.setColumn_index(workoutItem.getColumnIndex());
                            parseWorkoutItem.setRow_index(workoutItem.getRowIndex());
                            parseWorkoutItem.setName(workoutItem.getInput().getExercise());
                            parseWorkoutItem.setSets(workoutItem.getInput().getSets() == null
                                    ? null : workoutItem.getInput().getSets().intValue());
                            parseWorkoutItem.setRepetitions(workoutItem.getInput().getRepetitions() == null
                                    ? null : workoutItem.getInput().getRepetitions().intValue());
                            parseWorkoutItem.setWeight(workoutItem.getInput().getWeight() == null
                                    ? null : workoutItem.getInput().getWeight());
                            return parseWorkoutItem;
                        }).collect(Collectors.toList()));
                        return parseWorkout;
                    }).collect(Collectors.toList()));
            parseUser.setErrors(user.getErrors().stream().collect(Collectors.joining(", ")));
            parseUser.setProgram(program);
            return parseUser;
        }).collect(Collectors.toList());
    }

    ByteArrayInputStream dataUrlToInputStream(String dataUrl) {
        final String encodedString = dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH);
        return new ByteArrayInputStream(Base64.getDecoder().decode(encodedString));
    }

    ProgramResponseDTO delete(Long id) {
        final Program program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        final ProgramResponseDTO responseDTO = programToDto(program);
        programRepository.delete(program);
        return responseDTO;
    }
}
