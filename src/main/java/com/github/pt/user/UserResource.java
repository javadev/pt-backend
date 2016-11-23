package com.github.pt.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/user")
class UserResource {

    private final UserService userService;
    
    @Autowired
    UserResource(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    UserResponseDTO findOne(@RequestHeader(value = "X-Token") String token) {
        if (!token.isEmpty()) {
            return userService.findOne(token);
        }
        return new UserResponseDTO();
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void update(@RequestHeader(value = "X-Token") String token,
            @RequestBody UserRequestDTO userRequest) {
        userService.updateUser(token, userRequest);
    }
}
