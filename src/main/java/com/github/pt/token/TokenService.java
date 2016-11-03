package com.github.pt.token;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class TokenService {
    private final InUserRepository inUserRepository;
    private final InUserLoginRepository inUserLoginRepository;
    private final InUserFacebookRepository inUserFacebookRepository;
    private final OAuth20Service service;

    @Autowired
    TokenService(InUserRepository inUserRepository,
            InUserLoginRepository inUserLoginRepository,
            InUserFacebookRepository inUserFacebookRepository,
            @Value("social.facebook.apiKey") String apiKey,
            @Value("social.facebook.apiSecret") String apiSecret) {
        this.inUserRepository = inUserRepository;
        this.inUserLoginRepository = inUserLoginRepository;
        this.inUserFacebookRepository = inUserFacebookRepository;
        this.service = new ServiceBuilder()
                           .apiKey("your_api_key")
                           .apiSecret("your_api_secret")
                           .build(FacebookApi.instance());
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
        getResponseForProfile("EAACx6cfMR3UBAJb6pVXopI6ZCAgQsOBKtlWlm7fDYDzXPlZAoCSLnegJvlZCUrS19pt7BpK5A4zVgZCKpg5HfD6nugxBFhvWoW4BF2E7WHQ4nhykeZA28harguZBtuwS62nNi4o0W0Gx9bljpXReFLgXpq4K3Ls3yFtBrvpNhfjZC6rZAyaMETxoi8UlrPP1wpZCbkxemSZB9ABe6fCWqw2OD1GwnyOyoyZC8oZD");
        return tokenResponseDTO;        
    }
    
    private void getResponseForProfile(String accessTokenString) {
        try {
            final OAuth2AccessToken accessToken = new OAuth2AccessToken(accessTokenString);
            OAuthRequest request = new OAuthRequest(Verb.GET, "https://graph.facebook.com/me?fields=name,picture", service);
            service.signRequest(accessToken, request);
            Response response = request.send();
            String fbBody = response.getBody();
            JSONObject object = (JSONObject) new JSONTokener(fbBody).nextValue();
            String name = object.getString("name"); 
            System.out.println(fbBody); 
        } catch (IOException ex) {
            Logger.getLogger(TokenService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
