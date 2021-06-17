package com.osomapps.pt.admin.user;

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
@RequestMapping("api/v1/admin/user-program")
class AdminUserProgramResource {

    private final AdminUserProgramService userProgramService;

    @Autowired
    AdminUserProgramResource(AdminUserProgramService userProgramService) {
        this.userProgramService = userProgramService;
    }

    @GetMapping
    List<UserProgramResponseDTO> findAll() {
        return userProgramService.findAll();
    }

    @GetMapping("{id}")
    UserProgramResponseDTO findOne(@PathVariable Long id) {
        return userProgramService.findOne(id);
    }

    @PostMapping()
    UserProgramResponseDTO create(@RequestBody UserProgramRequestDTO userProgramRequestDTO) {
        return userProgramService.create(userProgramRequestDTO);
    }

    @PutMapping("{id}")
    UserProgramResponseDTO update(
            @PathVariable Long id, @RequestBody UserProgramRequestDTO userProgramRequestDTO) {
        return userProgramService.update(id, userProgramRequestDTO);
    }

    @DeleteMapping("{id}")
    UserProgramResponseDTO delete(@PathVariable Long id) {
        return userProgramService.delete(id);
    }
}
