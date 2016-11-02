package com.github.pt.token;

import com.github.pt.model.ResourceNotFoundException;
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

    @Autowired
    private InUserRepository inUserRepository;
    
    @Autowired
    private InUserLoginRepository inUserLoginRepository;

    @Autowired
    private InUserLogoutRepository inUserLogoutRepository;

    @Autowired
    private TokenService tokenService;    

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
            InUserLogout inUserLogout = new InUserLogout();
            inUserLogout.setToken(token);
            inUserLogout.setInUser(inUserLogins.get(inUserLogins.size() - 1).getInUser());
            inUserLogoutRepository.saveAndFlush(inUserLogout);
        } else {
            throw new ResourceNotFoundException("Token not found " + token);
        }
    }
}
