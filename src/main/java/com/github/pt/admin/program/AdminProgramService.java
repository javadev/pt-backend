package com.github.pt.admin.program;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.programs.Program;
import com.github.pt.programs.ProgramRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminProgramService {
    
    private final ProgramRepository programRepository;

    AdminProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    List<ProgramResponseDTO> findAll() {
        return programRepository.findAll().stream().map(program -> programToDto(program))
        .collect(Collectors.toList());
    }

    private ProgramResponseDTO programToDto(Program program) {
        return ProgramResponseDTO.builder()
                .id(program.getId())
                .name(program.getName())
                .dataUrl(program.getData_url())
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
        program.setData_url(programRequestDTO.getDataUrl());
        return programToDto(programRepository.save(program));
    }

    ProgramResponseDTO update(Long id, ProgramRequestDTO programRequestDTO) {
        final Program program = programRepository.findOne(id);
        if (program == null) {
            throw new ResourceNotFoundException("Program with id " + id + " not found");
        }
        program.setName(programRequestDTO.getName());
        program.setData_url(programRequestDTO.getDataUrl());
        return programToDto(programRepository.save(program));
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
