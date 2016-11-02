package com.github.pt.token;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class TokenService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserFacebookRepository inUserFacebookRepository;

    @Autowired
    TokenService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserFacebookRepository inUserFacebookRepository) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
    }

    TokenResponseDTO createOrReadNewToken(TokenRequestDTO tokenRequest) {
        final InUser inUser = new InUser();
        final InUserFacebook inUserFacebook = new InUserFacebook();
        inUserFacebook.setToken(tokenRequest.getFacebook_token());
        inUserFacebook.setDevice_id(tokenRequest.getDevice_id());
        final InUserLogin inUserLogin = new InUserLogin();
        inUser.setInUserFacebooks(Arrays.asList(inUserFacebook));
        inUser.setInUserLogins(Arrays.asList(inUserLogin)); 
        final InUser savedInUser = inUserRepository.saveAndFlush(inUser);
        inUserLogin.setInUser(savedInUser);
        inUserLoginRepository.saveAndFlush(inUserLogin);
        inUserFacebook.setInUser(inUser);
        inUserFacebookRepository.saveAndFlush(inUserFacebook);
        final TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.setToken(inUserLogin.getToken());
        tokenResponseDTO.setUser(inUser);
        return tokenResponseDTO;        
    }
}
