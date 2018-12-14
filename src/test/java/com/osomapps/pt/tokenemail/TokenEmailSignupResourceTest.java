package com.osomapps.pt.tokenemail;

import com.osomapps.pt.token.InUser;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class TokenEmailSignupResourceTest {

    @Mock
    private TokenEmailSignupService tokenEmailSignupService;

    @InjectMocks
    private TokenEmailSignupResource tokenEmailSignupResource;

    @Test
    public void list() {
        List<InUser> inUsers = tokenEmailSignupResource.list();
        assertThat(inUsers.isEmpty(), equalTo(true)); 
    }

    @Test
    public void create() {
        tokenEmailSignupResource.create(new TokenEmailSignupRequestDTO(), new MockHttpServletRequest());
        verify(tokenEmailSignupService).createNewToken(any(TokenEmailSignupRequestDTO.class), anyString());
    }
}
