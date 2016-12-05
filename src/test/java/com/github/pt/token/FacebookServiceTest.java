package com.github.pt.token;

import com.github.pt.UnauthorizedException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FacebookServiceTest {

    private FacebookService facebookService;

    @Test(expected = UnauthorizedException.class)
    public void getProfileNameAndId() {
        facebookService = new FacebookService();
        facebookService.getProfileNameAndId("");
    }

    @Test(expected = UnauthorizedException.class)
    public void getProfilePictureUrl() {
        facebookService = new FacebookService();
        facebookService.getProfilePictureUrl("");
    }

}
