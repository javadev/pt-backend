package com.osomapps.pt.token;

import com.osomapps.pt.UnauthorizedException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class FacebookServiceTest {

    private FacebookService facebookService;

    @Test
    public void getProfileNameAndId() {
        facebookService = new FacebookService();
        assertThrows(UnauthorizedException.class, () -> {facebookService.getProfileNameAndId("");});
    }

    @Test
    public void getProfilePictureUrl() {
        facebookService = new FacebookService();
        assertThrows(UnauthorizedException.class, () -> {facebookService.getProfilePictureUrl("");});
    }

}
