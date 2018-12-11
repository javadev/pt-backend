package com.osomapps.pt.tokenemail;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogout;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.util.Pair;
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
    @InjectMocks
    private TokenEmailService tokenEmailService;

    @Test
    public void createOrReadNewToken() {
        when(inUserEmailRepository.findByLogin(anyString())).thenReturn(Arrays.asList(
                new InUserEmail().setInUser(new InUser()
                        .setId(1L)
                        .setD_level("1")
                        .setInUserGoals(new ArrayList<>(Arrays.asList(new InUserGoal().setGoal_value("{\"key\":10}"))))
                        .setHeight(160F)
                        .setWeight(80F)
                        .setInUserEmails(new ArrayList<>())
                        .setInUserLogins(new ArrayList<>(Arrays.asList(new InUserLogin()))))));
        when(passwordEncoder.matches(anyObject(), anyObject())).thenReturn(Boolean.TRUE);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Collections.emptyList());
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        TokenEmailResponseDTO tokenEmailResponseDTO =
            tokenEmailService.createOrReadNewToken(
                new TokenEmailRequestDTO().setEmail("test@mail.com"), "");
        assertThat(tokenEmailResponseDTO, notNullValue());
    }

    @Test
    public void createOrReadNewToken_not_empty() {
        when(inUserEmailRepository.findByLogin(anyString())).thenReturn(Arrays.asList(
                new InUserEmail().setInUser(new InUser()
                        .setId(1L)
                        .setAge(20F)
                        .setD_level("1")
                        .setInUserGoals(Arrays.asList(new InUserGoal().setGoal_value("{\"key\":10}")))
                        .setInUserEmails(new ArrayList<>())
                        .setInUserLogins(new ArrayList<>(Arrays.asList(new InUserLogin()))))));
        when(passwordEncoder.matches(anyObject(), anyObject())).thenReturn(Boolean.TRUE);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogout()));
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        TokenEmailResponseDTO tokenEmailResponseDTO =
            tokenEmailService.createOrReadNewToken(
                new TokenEmailRequestDTO().setEmail("test@mail.com"), "");
        assertThat(tokenEmailResponseDTO, notNullValue());
    }

    @Test(expected = UnauthorizedException.class)
    public void readOrCreateInUserEmail_invalid_email() {
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
        when(passwordEncoder.matches(anyObject(), anyObject())).thenReturn(Boolean.TRUE);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogout()));
        Pair<Boolean, InUserEmail> pair = tokenEmailService.readOrCreateInUserEmail(
                new TokenEmailRequestDTO().setEmail("email"));
        assertThat(pair.getFirst(), equalTo(true));
    }

    @Test
    public void readOrCreateInUserEmail_empty() {
        when(inUserEmailRepository.findByLogin(eq("email"))).thenReturn(Arrays.asList(new InUserEmail()
            .setInUser(new InUser().setInUserLogins(Arrays.asList(new InUserLogin())))));
        when(passwordEncoder.matches(anyObject(), anyObject())).thenReturn(Boolean.TRUE);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Collections.emptyList());
        Pair<Boolean, InUserEmail> pair = tokenEmailService.readOrCreateInUserEmail(
                new TokenEmailRequestDTO().setEmail("email"));
        assertThat(pair.getFirst(), equalTo(false));
    }

    @Test(expected = UnauthorizedException.class)
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
