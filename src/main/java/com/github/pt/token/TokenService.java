package com.github.pt.token;

import com.github.pt.ResourceNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
class TokenService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserFacebookRepository inUserFacebookRepository;
    private final FacebookService facebookService;

    @Autowired
    TokenService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserFacebookRepository inUserFacebookRepository,
            FacebookService facebookService) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
        this.facebookService = facebookService;
    }

    TokenResponseDTO createOrReadNewToken(TokenRequestDTO tokenRequest) {
        final InUser inUser;
        final InUserFacebook inUserFacebook = new InUserFacebook();
        inUserFacebook.setToken(tokenRequest.getFacebook_token());
        inUserFacebook.setDevice_id(tokenRequest.getDevice_id());
        Pair<String, String> nameAndId = 
            facebookService.getProfileNameAndId(tokenRequest.getFacebook_token())
                .orElseThrow(() -> new ResourceNotFoundException("Can't load data from facebook"));
        Optional<String> pictureUrl = 
            facebookService.getProfilePictureUrl(tokenRequest.getFacebook_token());
        inUserFacebook.setUser_name(nameAndId.getFirst());
        inUserFacebook.setUserId(nameAndId.getSecond());
        inUserFacebook.setPicture_url(pictureUrl.orElse(null));
        List<InUserFacebook> inUserFacebooks = inUserFacebookRepository.findByUserId(nameAndId.getSecond());
        final InUserLogin inUserLogin = new InUserLogin();
        if (inUserFacebooks.isEmpty()) {
            inUser = new InUser();
            inUser.setInUserFacebooks(Arrays.asList(inUserFacebook));
            inUser.setInUserLogins(Arrays.asList(inUserLogin)); 
        } else {
            inUser = inUserFacebooks.get(inUserFacebooks.size() - 1).getInUser();
            inUser.getInUserFacebooks().add(inUserFacebook);
            inUser.getInUserLogins().add(inUserLogin);
        } 
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
