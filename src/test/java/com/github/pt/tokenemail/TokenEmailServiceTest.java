package com.github.pt.tokenemail;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserLogin;
import com.github.pt.token.InUserLoginRepository;
import com.github.pt.token.InUserLogout;
import com.github.pt.token.InUserLogoutRepository;
import com.github.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.util.Pair;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

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

    @Test(expected = UnauthorizedException.class)
    public void readOrCreateInUserEmail_invalid_email() {
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("email", "Invalid empty email");
            return null;
        }).when(emailValidator).validate(anyObject(), any(Errors.class));
        when(inUserEmailRepository.findByLogin(eq("email"))).thenReturn(Collections.emptyList());
        tokenEmailService.readOrCreateInUserEmail(new TokenEmailRequestDTO().setEmail("email"));
    }

    @Test(expected = UnauthorizedException.class)
    public void readOrCreateInUserEmail_wrong_password() {
        when(inUserEmailRepository.findByLogin(eq("email"))).thenReturn(Arrays.asList(new InUserEmail()));
        tokenEmailService.readOrCreateInUserEmail(new TokenEmailRequestDTO().setEmail("email"));
    }

    @Test
    public void readOrCreateInUserEmail() {
        when(inUserEmailRepository.findByLogin(eq("email"))).thenReturn(Arrays.asList(new InUserEmail()
            .setInUser(new InUser().setInUserLogins(Arrays.asList(new InUserLogin())))));
        when(passwordEncoder.matches(anyObject(), anyString())).thenReturn(Boolean.TRUE);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogout()));
        Pair<Boolean, InUserEmail> pair = tokenEmailService.readOrCreateInUserEmail(
                new TokenEmailRequestDTO().setEmail("email"));
        assertThat(pair.getFirst(), equalTo(true));
    }

    @Test
    public void readOrCreateInUserEmail_empty() {
        when(inUserEmailRepository.findByLogin(eq("email"))).thenReturn(Arrays.asList(new InUserEmail()
            .setInUser(new InUser().setInUserLogins(Arrays.asList(new InUserLogin())))));
        when(passwordEncoder.matches(anyObject(), anyString())).thenReturn(Boolean.TRUE);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Collections.emptyList());
        Pair<Boolean, InUserEmail> pair = tokenEmailService.readOrCreateInUserEmail(
                new TokenEmailRequestDTO().setEmail("email"));
        assertThat(pair.getFirst(), equalTo(false));
    }

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

    @Test(expected = UnauthorizedException.class)
    public void deleteToken_invalid() {
        InUserLogin inUserLogin = new InUserLogin();
        when(inUserLoginRepository.findByToken(anyString())).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogout()));
        tokenEmailService.deleteToken("1", "");
    }

}
