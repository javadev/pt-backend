package com.github.pt.token;

import com.github.pt.UnauthorizedException;
import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.IOException;
import java.util.Optional;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

@Service
class FacebookService {
    private static final Logger LOG = LoggerFactory.getLogger(FacebookService.class);
    private static final String PICTURE_URL = "https://graph.facebook.com/me/picture?redirect=false&type=large";
    private static final String NAME_URL = "https://graph.facebook.com/me?fields=name&locale=en_US";
    private final OAuth20Service service;

    FacebookService() {
        this.service = new ServiceBuilder()
                           .apiKey("your_api_key")
                           .apiSecret("your_api_secret")
                           .build(FacebookApi.instance());
    }
    
    public Optional<Pair<String, String>> getProfileNameAndId(String accessTokenString) {
        try {
            final OAuth2AccessToken accessToken = new OAuth2AccessToken(accessTokenString);
            final OAuthRequest requestPicture = new OAuthRequest(Verb.GET, NAME_URL, service);
            service.signRequest(accessToken, requestPicture);
            final Response response = requestPicture.send();
            if (!response.isSuccessful()) {
                throw new UnauthorizedException("Error while requesting data from facebook: "
                        + response.getMessage());
            }
            final String pictureBody = response.getBody();
            final JSONObject object = (JSONObject) new JSONTokener(pictureBody).nextValue();
            return Optional.of(Pair.of(object.getString("name"), object.getString("id")));
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            return Optional.empty();
        }
    }

    public Optional<String> getProfilePictureUrl(String accessTokenString) {
        try {
            final OAuth2AccessToken accessToken = new OAuth2AccessToken(accessTokenString);
            final OAuthRequest requestPicture = new OAuthRequest(Verb.GET, PICTURE_URL, service);
            service.signRequest(accessToken, requestPicture);
            final Response response = requestPicture.send();
            if (!response.isSuccessful()) {
                throw new UnauthorizedException(response.getMessage());
            }
            final String pictureBody = response.getBody();
            final JSONObject object = (JSONObject) new JSONTokener(pictureBody).nextValue();
            return Optional.ofNullable(object.getJSONObject("data").getString("url"));
        } catch (IOException ex) {
            LOG.error(ex.getMessage(), ex);
            return Optional.empty();
        }
    }
}
