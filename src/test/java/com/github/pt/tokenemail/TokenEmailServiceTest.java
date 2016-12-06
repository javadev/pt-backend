package com.github.pt.tokenemail;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.token.InUserLogin;
import com.github.pt.token.InUserLoginRepository;
import com.github.pt.token.InUserLogoutRepository;
import com.github.pt.token.InUserRepository;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@RunWith(MockitoJUnitRunner.class)
public class TokenEmailServiceTest {

    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InUserEmailRepository inUserEmailRepository;
    @Mock
    private InUserLoginRepository inUserLoginRepository;
    @Mock
    private InUserLogoutRepository inUserLogoutRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailValidator emailValidator;
    @Mock
    private SendEmailService sendEmailService;
    @InjectMocks
    private TokenEmailService tokenEmailService;

    @Test(expected = ResourceNotFoundException.class)
    public void deleteToken_not_found() {
        tokenEmailService.deleteToken("1", "");
    }

    @Test
    public void deleteToken() {
        InUserLogin inUserLogin = new InUserLogin();
        when(inUserLoginRepository.findByToken(anyString())).thenReturn(Arrays.asList(inUserLogin));
        tokenEmailService.deleteToken("1", "");
        verify(inUserLogoutRepository).saveAndFlush(any());
    }
}
