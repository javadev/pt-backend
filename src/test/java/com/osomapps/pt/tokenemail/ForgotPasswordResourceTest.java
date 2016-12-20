package com.osomapps.pt.tokenemail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class ForgotPasswordResourceTest {

    @Mock
    private TokenEmailSignupService tokenEmailSignupService;    

    @InjectMocks
    private ForgotPasswordResource forgotPasswordResource;

    @Test
    public void create() {
        forgotPasswordResource.create(new ForgotPasswordRequestDTO(), new MockHttpServletRequest());
        verify(tokenEmailSignupService).forgotPassword(any(ForgotPasswordRequestDTO.class), anyString());
    }
}
