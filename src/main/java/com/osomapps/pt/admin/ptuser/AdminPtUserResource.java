package com.osomapps.pt.admin.ptuser;

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
@RequestMapping("api/v1/admin/pt-user")
class AdminPtUserResource {

    private final AdminPtUserService ptUserService;
    
    @Autowired
    AdminPtUserResource(AdminPtUserService ptUserService) {
        this.ptUserService = ptUserService;
    }

    @GetMapping
    List<PtUserResponseDTO> findAll() {
        return ptUserService.findAll();
    }

    @GetMapping("{id}")
    PtUserResponseDTO findOne(@PathVariable Long id) {
        return ptUserService.findOne(id);
    }

    @PostMapping
    PtUserResponseDTO create(@RequestBody PtUserRequestDTO ptUserRequestDTO) {
        return ptUserService.create(ptUserRequestDTO);
    }

    @PutMapping("{id}")
    PtUserResponseDTO update(@PathVariable Long id, @RequestBody PtUserRequestDTO ptUserRequestDTO) {
        return ptUserService.update(id, ptUserRequestDTO);
    }

    @DeleteMapping("{id}")
    PtUserResponseDTO delete(@PathVariable Long id) {
        return ptUserService.delete(id);
    }

}
