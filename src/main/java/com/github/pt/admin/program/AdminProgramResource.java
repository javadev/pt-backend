package com.github.pt.admin.program;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/program")
class AdminProgramResource {

    private final AdminProgramService programService;
    
    @Autowired
    AdminProgramResource(AdminProgramService programService) {
        this.programService = programService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<ProgramResponseDTO> findAll() {
        return programService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ProgramResponseDTO findOne(@PathVariable Long id) {
        return programService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    ProgramResponseDTO create(@RequestBody ProgramRequestDTO programRequestDTO) {
        return programService.create(programRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    ProgramResponseDTO update(@PathVariable Long id, @RequestBody ProgramRequestDTO exerciseRequestDTO) {
        return programService.update(id, exerciseRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    ProgramResponseDTO delete(@PathVariable Long id) {
        return programService.delete(id);
    }

}
