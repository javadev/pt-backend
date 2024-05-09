package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import com.osomapps.pt.token.InUser;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class TokenEmailSignupResourceTest {

    @Mock private TokenEmailSignupService tokenEmailSignupService;

    @InjectMocks private TokenEmailSignupResource tokenEmailSignupResource;

    @Test
    public void list() {
        List<InUser> inUsers = tokenEmailSignupResource.list();
        assertThat(inUsers.isEmpty(), equalTo(true));
    }

    @Test
    public void create() {
        tokenEmailSignupResource.create(
                new TokenEmailSignupRequestDTO(), new MockHttpServletRequest());
        verify(tokenEmailSignupService)
                .createNewToken(any(TokenEmailSignupRequestDTO.class), anyString());
    }
}
