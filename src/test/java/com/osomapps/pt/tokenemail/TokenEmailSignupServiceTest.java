package com.osomapps.pt.tokenemail;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

@ExtendWith(MockitoExtension.class)
public class TokenEmailSignupServiceTest {

    @Mock
    private SendEmailService sendEmailService;
    @Mock
    private InUserEmailRepository inUserEmailRepository;
    @Mock
    private EmailValidator emailValidator;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InUserLoginRepository inUserLoginRepository;
    @Mock
    private DataurlValidator dataurlValidator;
    @InjectMocks
    private TokenEmailSignupService tokenEmailSignupService;

    @Test
    public void createInUserEmail() {
        tokenEmailSignupService.createInUserEmail(new TokenEmailSignupRequestDTO().setUser(
                new UserSignupRequestDTO().setEmail("test@mail.com")));
    }

    @Test
    public void createInUserEmail_wrong_email() {
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("emailValidator", "emailValidator");
            return null;
        }).when(emailValidator).validate(anyObject(), any(Errors.class));
        assertThrows(UnauthorizedException.class, () -> {tokenEmailSignupService.createInUserEmail(new TokenEmailSignupRequestDTO().setUser(
                new UserSignupRequestDTO().setEmail("test@mail.com")));});
    }

    @Test
    public void createInUserEmail_wrong_dataurl() {
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("dataurlValidator", "dataurlValidator");
            return null;
        }).when(dataurlValidator).validate(anyObject(), any(Errors.class));
        assertThrows(UnauthorizedException.class, () -> {tokenEmailSignupService.createInUserEmail(new TokenEmailSignupRequestDTO().setUser(
                new UserSignupRequestDTO().setEmail("test@mail.com")));});
    }

    @Test
    public void createInUserEmail_already_registered() {
        when(inUserEmailRepository.findByLogin(anyString())).thenReturn(Arrays.asList(new InUserEmail()));
        assertThrows(UnauthorizedException.class, () -> {tokenEmailSignupService.createInUserEmail(new TokenEmailSignupRequestDTO().setUser(
                new UserSignupRequestDTO().setEmail("test@mail.com")));});
    }

    @Test
    public void createNewToken() {
        when(inUserRepository.save((InUser) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        when(inUserEmailRepository.save((InUserEmail) anyObject())).thenAnswer(i -> i.getArguments()[0]);
        tokenEmailSignupService.createNewToken(new TokenEmailSignupRequestDTO().setUser(
                new UserSignupRequestDTO().setEmail("test@mail.com")), "");
    }

    @Test
    public void confirmToken() {
        assertThat(tokenEmailSignupService.confirmToken(""), equalTo(false));
    }

    @Test
    public void confirmToken_true() {
        when(inUserEmailRepository.findByConfirmToken(anyString())).thenReturn(Arrays.asList(
                new InUserEmail().setIs_confirmed(Boolean.FALSE)));
        assertThat(tokenEmailSignupService.confirmToken(""), equalTo(true));
    }

    @Test
    public void confirmToken_false() {
        when(inUserEmailRepository.findByConfirmToken(anyString())).thenReturn(Arrays.asList(
                new InUserEmail().setIs_confirmed(Boolean.TRUE)));
        assertThat(tokenEmailSignupService.confirmToken(""), equalTo(true));
    }

    @Test
    public void forgotPassword() {
        assertThrows(UnauthorizedException.class, () -> {tokenEmailSignupService.forgotPassword(new ForgotPasswordRequestDTO().setEmail("test@mail.com"), "");});
    }

    @Test
    public void forgotPassword_true() {
        when(inUserEmailRepository.findByLogin(anyString())).thenReturn(Arrays.asList(
                new InUserEmail().setIs_confirmed(Boolean.FALSE)));
        tokenEmailSignupService.forgotPassword(new ForgotPasswordRequestDTO().setEmail("test@mail.com"), "");
        verify(inUserEmailRepository).save(any(InUserEmail.class));
    }

    @Test
    public void resetToken() {
        assertThat(tokenEmailSignupService.resetToken(""), equalTo(Optional.empty()));
    }
    
    @Test
    public void resetToken_true() {
        when(inUserEmailRepository.findByResetToken(anyString())).thenReturn(Arrays.asList(
                new InUserEmail().setIs_confirmed(Boolean.FALSE)));
        assertThat(tokenEmailSignupService.resetToken(""), notNullValue());
    }
}
