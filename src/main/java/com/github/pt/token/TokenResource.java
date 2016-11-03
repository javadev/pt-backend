package com.github.pt.token;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
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
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserLogoutRepository inUserLogoutRepository;
    private final TokenService tokenService;
    
    @Autowired
    public TokenResource(InUserRepository inUserRepository, 
            InUserLoginRepository inUserLoginRepository,
            InUserLogoutRepository inUserLogoutRepository,
            TokenService tokenService) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserLogoutRepository = inUserLogoutRepository;
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
        final List<InUserLogin> inUserLogins = inUserLoginRepository.findByToken(token);
        if (!inUserLogins.isEmpty()) {
            final List<InUserLogout> inUserLogouts = inUserLogoutRepository.findByToken(token);
            if (!inUserLogouts.isEmpty()) {
                throw new UnauthorizedException("Invalid token");
            }
            InUserLogout inUserLogout = new InUserLogout();
            inUserLogout.setToken(token);
            inUserLogout.setInUser(inUserLogins.get(inUserLogins.size() - 1).getInUser());
            inUserLogoutRepository.saveAndFlush(inUserLogout);
        } else {
            throw new ResourceNotFoundException("Token not found " + token);
        }
    }
}
