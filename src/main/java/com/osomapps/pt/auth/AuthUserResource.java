package com.osomapps.pt.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth/user")
class AuthUserResource {

    private final AuthUserService authUserService;

    @Autowired
    AuthUserResource(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @GetMapping
    AuthUserResponseDTO findOne() {
        return authUserService.findOne();
    }
}
