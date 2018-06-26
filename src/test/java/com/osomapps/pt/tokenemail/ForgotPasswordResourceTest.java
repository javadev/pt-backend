package com.osomapps.pt.tokenemail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;

@ExtendWith(MockitoExtension.class)
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
