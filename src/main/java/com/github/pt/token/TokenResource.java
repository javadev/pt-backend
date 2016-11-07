package com.github.pt.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("api/v1/token")
public class TokenResource {

    private final InUserRepository inUserRepository;
    private final TokenService tokenService;
    
    @Autowired
    public TokenResource(InUserRepository inUserRepository, 
            TokenService tokenService) {
        this.inUserRepository = inUserRepository;
        this.tokenService = tokenService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<InUser> list() {
        return inUserRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public TokenResponseDTO create(@RequestBody TokenRequestDTO tokenRequest) {
        return tokenService.createOrReadNewToken(tokenRequest);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@RequestHeader(value = "X-Token") String token) {
        tokenService.deleteToken(token);
    }
}
