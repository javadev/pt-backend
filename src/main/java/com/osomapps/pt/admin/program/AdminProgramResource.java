package com.osomapps.pt.admin.program;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/program")
class AdminProgramResource {

    private final AdminProgramService programService;
    
    @Autowired
    AdminProgramResource(AdminProgramService programService) {
        this.programService = programService;
    }

    @GetMapping
    List<ProgramResponseDTO> findAll() {
        return programService.findAll();
    }

    @GetMapping("{id}")
    ProgramResponseDTO findOne(@PathVariable Long id) {
        return programService.findOne(id);
    }

    @PostMapping
    ProgramResponseDTO create(@RequestBody ProgramRequestDTO programRequestDTO) {
        return programService.create(programRequestDTO);
    }

    @PutMapping("{id}")
    ProgramResponseDTO update(@PathVariable Long id, @RequestBody ProgramRequestDTO exerciseRequestDTO) {
        return programService.update(id, exerciseRequestDTO);
    }

    @DeleteMapping("{id}")
    ProgramResponseDTO delete(@PathVariable Long id) {
        return programService.delete(id);
    }

}
