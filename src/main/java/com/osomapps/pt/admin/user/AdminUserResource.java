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
@RequestMapping("api/v1/admin/user")
class AdminUserResource {

    private final AdminUserService userService;

    @Autowired
    AdminUserResource(AdminUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    UserResponseDTO findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @PostMapping
    UserResponseDTO create(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO);
    }

    @PutMapping("{id}")
    UserResponseDTO update(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.update(id, userRequestDTO);
    }

    @DeleteMapping("{id}")
    UserResponseDTO delete(@PathVariable Long id) {
        return userService.delete(id);
    }
}
