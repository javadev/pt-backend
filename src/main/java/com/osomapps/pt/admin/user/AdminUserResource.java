package com.osomapps.pt.admin.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user")
class AdminUserResource {

    private final AdminUserService userService;
    
    @Autowired
    AdminUserResource(AdminUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    UserResponseDTO findOne(@PathVariable Long id) {
        return userService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    UserResponseDTO create(@RequestBody UserRequestDTO userRequestDTO) {
        return userService.create(userRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    UserResponseDTO update(@PathVariable Long id, @RequestBody UserRequestDTO userRequestDTO) {
        return userService.update(id, userRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    UserResponseDTO delete(@PathVariable Long id) {
        return userService.delete(id);
    }

}
