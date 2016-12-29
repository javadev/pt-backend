package com.osomapps.pt.programs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/v1/programs")
class ProgramsResource {

    private final ProgramService programService;
    
    @Autowired
    ProgramsResource(ProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    List<ProgramResponseDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return programService.getPredefinedPrograms(token);
    }
}
