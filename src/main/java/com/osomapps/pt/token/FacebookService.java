package com.osomapps.pt.token;

import static java.time.temporal.ChronoUnit.YEARS;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.osomapps.pt.UnauthorizedException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class FacebookService {
    private static final String PICTURE_URL =
            "https://graph.facebook.com/me/picture?redirect=false&type=large";
    private static final String NAME_URL =
            "https://graph.facebook.com/me?fields=name,gender,birthday&locale=en_US";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private OAuth20Service service;
    private OAuthRequest requestName;
    private OAuthRequest requestPicture;

    FacebookService() {
        service =
                new ServiceBuilder()
                        .apiKey("your_api_key")
                        .apiSecret("your_api_secret")
                        .build(FacebookApi.instance());
        requestName = new OAuthRequest(Verb.GET, NAME_URL, service);
        requestPicture = new OAuthRequest(Verb.GET, PICTURE_URL, service);
    }

    @SuppressWarnings("unchecked")
    Optional<FacebookResponse> getProfileNameAndId(String accessTokenString) {
        try {
            final OAuth2AccessToken accessToken = new OAuth2AccessToken(accessTokenString);
            service.signRequest(accessToken, requestName);
            final Response response = requestName.send();
            if (!response.isSuccessful()) {
                throw new UnauthorizedException(
                        "Error while requesting data from facebook: " + response.getMessage());
            }
            org.springframework.boot.json.JsonParser springParser =
                    org.springframework.boot.json.JsonParserFactory.getJsonParser();
            Map<String, Object> object = springParser.parseMap(response.getBody());
            LocalDate birthday = LocalDate.parse(String.valueOf(object.get("birthday")), FORMATTER);
            final FacebookResponse facebookResponse =
                    new FacebookResponse(
                            String.valueOf(object.get("id")),
                            String.valueOf(object.get("name")),
                            String.valueOf(object.get("gender")),
                            birthday,
                            YEARS.between(birthday, LocalDate.now()));
            return Optional.of(facebookResponse);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            return Optional.empty();
        }
    }

    @SuppressWarnings("unchecked")
    Optional<String> getProfilePictureUrl(String accessTokenString) {
        try {
            final OAuth2AccessToken accessToken = new OAuth2AccessToken(accessTokenString);
            service.signRequest(accessToken, requestPicture);
            final Response response = requestPicture.send();
            if (!response.isSuccessful()) {
                throw new UnauthorizedException(response.getMessage());
            }
            final String pictureBody = response.getBody();
            org.springframework.boot.json.JsonParser springParser =
                    org.springframework.boot.json.JsonParserFactory.getJsonParser();
            Map<String, Object> object = springParser.parseMap(pictureBody);
            return Optional.ofNullable(
                    String.valueOf(((Map<String, Object>) object.get("data")).get("url")));
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
            return Optional.empty();
        }
    }
}
