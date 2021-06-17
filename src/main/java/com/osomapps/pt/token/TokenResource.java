package com.osomapps.pt.token;

import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/token")
class TokenResource {

    private final TokenService tokenService;

    @Autowired
    TokenResource(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping
    List<InUser> list() {
        return Collections.emptyList();
    }

    @PostMapping
    TokenResponseDTO create(@RequestBody TokenRequestDTO tokenRequest, HttpServletRequest request) {
        return tokenService.createOrReadNewToken(tokenRequest, request.getRemoteAddr());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@RequestHeader(value = "X-Token") String token, HttpServletRequest request) {
        tokenService.deleteToken(token, request.getRemoteAddr());
    }
}
