package com.osomapps.pt.token;

import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.oauth.OAuth20Service;
import java.io.InputStream;
import java.util.HashMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(MockitoJUnitRunner.class)
public class FacebookServiceTest {

    private FacebookService facebookService;

    @Test
    public void getProfileNameAndId() {
        facebookService = new FacebookService();
        ReflectionTestUtils.setField(facebookService, "service", mock(OAuth20Service.class));
        OAuthRequest requestName = mock(OAuthRequest.class);
        Response response = new Response(200, "OK", new HashMap<>(),
            "{\"birthday\":\"01/01/1985\"}", mock(InputStream.class));
        when(requestName.send()).thenReturn(response);
        ReflectionTestUtils.setField(facebookService, "requestName", requestName);
        facebookService.getProfileNameAndId("");
    }

    @Test
    public void getProfilePictureUrl() {
        facebookService = new FacebookService();
        ReflectionTestUtils.setField(facebookService, "service", mock(OAuth20Service.class));
        OAuthRequest requestPicture = mock(OAuthRequest.class);
        Response response = new Response(200, "OK", new HashMap<>(),
            "{\"data\":{\"url\":\"url\"}}", mock(InputStream.class));
        when(requestPicture.send()).thenReturn(response);
        ReflectionTestUtils.setField(facebookService, "requestPicture", requestPicture);
        facebookService.getProfilePictureUrl("");
    }

}
