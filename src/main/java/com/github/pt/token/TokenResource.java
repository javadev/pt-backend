package com.github.pt.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/token")
class TokenResource {

    private final InUserRepository inUserRepository;
    private final TokenService tokenService;
    
    @Autowired
    TokenResource(InUserRepository inUserRepository, 
            TokenService tokenService) {
        this.inUserRepository = inUserRepository;
        this.tokenService = tokenService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<InUser> list() {
        return inUserRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    TokenResponseDTO create(@RequestBody TokenRequestDTO tokenRequest, HttpServletRequest request) {
        return tokenService.createOrReadNewToken(tokenRequest, request.getRemoteAddr());
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void delete(@RequestHeader(value = "X-Token") String token, HttpServletRequest request) {
        tokenService.deleteToken(token, request.getRemoteAddr());
    }
}
