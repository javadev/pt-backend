package com.github.pt.token;

import com.github.pt.model.ResourceNotFoundException;
import java.util.Arrays;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/inuser")
public class TokenResource {

    @Autowired
    private InUserRepository inUserRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<InUser> list() {
        return inUserRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public TokenResponseDTO create(@RequestBody TokenRequestDTO tokenRequest) {
        final InUser inUser = new InUser();
        final InUserFacebook inUserFacebook = new InUserFacebook();
        inUserFacebook.setToken(tokenRequest.getFacebook_token());
        inUserFacebook.setDevice_id(tokenRequest.getDevice_id());
        inUserFacebook.setInUser(inUser);
        final InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(inUser);
        inUser.setInUserFacebooks(Arrays.asList(inUserFacebook));
        inUser.setInUserLogins(Arrays.asList(inUserLogin)); 
        inUserRepository.saveAndFlush(inUser);
        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setToken(inUserLogin.getToken());
        return tokenResponseDTO;        
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public InUser get(@PathVariable Long id) {
        InUser found = inUserRepository.findOne(id);
        if (found == null) {
            throw new ResourceNotFoundException();
        } else {
            return found;
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public InUser update(@PathVariable Long id, @RequestBody InUser shipwreck) {
        InUser existing = inUserRepository.findOne(id);
        BeanUtils.copyProperties(shipwreck, existing);
        return inUserRepository.saveAndFlush(existing);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public InUser delete(@PathVariable Long id) {
        InUser existing = inUserRepository.findOne(id);
        inUserRepository.delete(id);
        return existing;
    }
}
