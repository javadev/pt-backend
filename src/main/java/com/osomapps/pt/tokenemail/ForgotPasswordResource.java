package com.osomapps.pt.tokenemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/forgot-password")
class ForgotPasswordResource {

    private final TokenEmailSignupService tokenEmailSignupService;
    
    @Autowired
    ForgotPasswordResource(TokenEmailSignupService tokenEmailSignupService) {
        this.tokenEmailSignupService = tokenEmailSignupService;
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void create(@RequestBody ForgotPasswordRequestDTO forgotPasswordRequestDTO,
            HttpServletRequest request) {
        tokenEmailSignupService.forgotPassword(forgotPasswordRequestDTO, request.getRemoteAddr());
    }
}
