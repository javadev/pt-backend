package com.github.pt.admin.program;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.programs.ParseResult;
import com.github.pt.programs.ParseResultRepository;
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

@Service
class AdminProgramService {
    
    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;
    private final ProgramRepository programRepository;
    private final ParseResultRepository parseResultRepository;

    AdminProgramService(ProgramRepository programRepository,
            ParseResultRepository parseResultRepository) {
        this.programRepository = programRepository;
        this.parseResultRepository = parseResultRepository;
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
                .parseResults(program.getParseResults().stream().map(result -> ParseResultDTO.builder()
                    .id(result.getId())
                    .userName(result.getUser_name())
                    .workouts(result.getWorkouts())
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
        program.setParseResults(parseResultRepository.save(parseDataUrl(programRequestDTO, savedProgram)));
        return programToDto(program);
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
        parseResultRepository.delete(program.getParseResults());
        program.setParseResults(parseResultRepository.save(parseDataUrl(programRequestDTO, program)));
        return programToDto(programRepository.save(program));
    }
    
    private List<ParseResult> parseDataUrl(ProgramRequestDTO programRequestDTO, final Program program) {
        final ByteArrayInputStream arrayInputStream = dataUrlToInputStream(programRequestDTO.getDataUrl());
        final XlsxParser xlsxParser = XlsxParser.of(arrayInputStream);
        final List<ExcelUser> excelUsers = xlsxParser.getExcelUsers();
        return excelUsers.stream().map(user -> {
            final ParseResult parseResult = new ParseResult();
            parseResult.setUser_name(user.getName());
            parseResult.setWorkouts(user.getWorkouts().stream()
                    .map(workout -> workout.getName()).collect(Collectors.joining(", ")));
            parseResult.setErrors(user.getErrors().stream().collect(Collectors.joining(", ")));
            parseResult.setProgram(program);
            return parseResult;
        }).collect(Collectors.toList());
    }
    
    private ByteArrayInputStream dataUrlToInputStream(String dataUrl) {
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
