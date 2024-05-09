package com.osomapps.pt.token;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.UnauthorizedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {

    @Mock private InUserRepository inUserRepository;
    @Mock private InUserLoginRepository inUserLoginRepository;
    @Mock private InUserLogoutRepository inUserLogoutRepository;
    @Mock private InUserFacebookRepository inUserFacebookRepository;
    @Mock private FacebookService facebookService;

    @InjectMocks private TokenService tokenService;

    @Test
    public void createOrReadNewToken_new_registration() {
        TokenRequestDTO tokenRequest = new TokenRequestDTO();
        tokenRequest.setFacebook_token("token");
        tokenRequest.setDevice_id("device_id");
        FacebookResponse facebookResponse = new FacebookResponse();
        facebookResponse.setAge(20L);
        when(facebookService.getProfileNameAndId(anyString()))
                .thenReturn(Optional.of(facebookResponse));
        when(facebookService.getProfilePictureUrl(anyString())).thenReturn(Optional.empty());
        when(inUserRepository.save(any(InUser.class))).thenReturn(null);
        when(inUserLoginRepository.saveAndFlush(any(InUserLogin.class))).thenReturn(null);
        when(inUserFacebookRepository.save(any(InUserFacebook.class))).thenReturn(null);
        TokenResponseDTO tokenResponseDTO = tokenService.createOrReadNewToken(tokenRequest, "");
        assertThat(tokenResponseDTO.getToken().startsWith("pt-"), equalTo(true));
        verify(facebookService).getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService).getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
        verify(inUserRepository).save(any(InUser.class));
        verify(inUserLoginRepository).saveAndFlush(any(InUserLogin.class));
        verify(inUserFacebookRepository).save(any(InUserFacebook.class));
    }

    @Test
    public void createOrReadNewToken_new_registration2() {
        TokenRequestDTO tokenRequest = new TokenRequestDTO();
        tokenRequest.setFacebook_token("token");
        tokenRequest.setDevice_id("device_id");
        FacebookResponse facebookResponse = new FacebookResponse();
        facebookResponse.setAge(20L);
        when(inUserFacebookRepository.findByUserId(any()))
                .thenReturn(
                        Arrays.asList(
                                new InUserFacebook()
                                        .setInUser(
                                                new InUser()
                                                        .setD_level("1")
                                                        .setInUserGoals(
                                                                Arrays.asList(
                                                                        new InUserGoal()
                                                                                .setGoal_value(
                                                                                        "{\"key\":10}")))
                                                        .setInUserFacebooks(
                                                                new ArrayList<>(
                                                                        Arrays.asList(
                                                                                new InUserFacebook())))
                                                        .setInUserLogins(
                                                                new ArrayList<>(
                                                                        Arrays.asList(
                                                                                new InUserLogin()))))));
        when(facebookService.getProfileNameAndId(anyString()))
                .thenReturn(Optional.of(facebookResponse));
        when(facebookService.getProfilePictureUrl(anyString())).thenReturn(Optional.empty());
        when(inUserRepository.save(any(InUser.class))).thenReturn(null);
        when(inUserLoginRepository.saveAndFlush(any(InUserLogin.class))).thenReturn(null);
        when(inUserFacebookRepository.save(any(InUserFacebook.class))).thenReturn(null);
        TokenResponseDTO tokenResponseDTO = tokenService.createOrReadNewToken(tokenRequest, "");
        assertThat(tokenResponseDTO.getToken().startsWith("pt-"), equalTo(true));
        verify(facebookService).getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService).getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
        verify(inUserRepository).save(any(InUser.class));
        verify(inUserLoginRepository).saveAndFlush(any(InUserLogin.class));
        verify(inUserFacebookRepository).save(any(InUserFacebook.class));
    }

    @Test
    public void createOrReadNewToken_twice_registration() {
        TokenRequestDTO tokenRequest = new TokenRequestDTO();
        tokenRequest.setFacebook_token("token");
        tokenRequest.setDevice_id("device_id");
        FacebookResponse facebookResponse = new FacebookResponse();
        facebookResponse.setAge(20L);
        when(facebookService.getProfileNameAndId(anyString()))
                .thenReturn(Optional.of(facebookResponse));
        when(facebookService.getProfilePictureUrl(anyString())).thenReturn(Optional.empty());
        when(inUserRepository.save(any(InUser.class))).thenReturn(null);
        when(inUserLoginRepository.saveAndFlush(any(InUserLogin.class))).thenReturn(null);
        when(inUserFacebookRepository.save(any(InUserFacebook.class))).thenReturn(null);
        InUserFacebook inUserFacebook = new InUserFacebook();
        InUser inUser = new InUser();
        inUserFacebook.setInUser(inUser);
        InUserLogin inUserLogin = new InUserLogin();
        inUser.setInUserLogins(Arrays.asList(inUserLogin));
        when(inUserFacebookRepository.findByDeviceId(anyString()))
                .thenReturn(Arrays.asList(inUserFacebook));
        TokenResponseDTO tokenResponseDTO = tokenService.createOrReadNewToken(tokenRequest, "");
        TokenResponseDTO tokenResponseDTO2 = tokenService.createOrReadNewToken(tokenRequest, "");
        assertThat(tokenResponseDTO.getToken(), equalTo(tokenResponseDTO2.getToken()));
        verify(facebookService, times(2))
                .getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService, times(2))
                .getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
        verify(inUserRepository, times(2)).save(any(InUser.class));
        verify(inUserLoginRepository, times(2)).saveAndFlush(any(InUserLogin.class));
        verify(inUserFacebookRepository, times(2)).save(any(InUserFacebook.class));
    }

    @Test
    public void createOrReadNewToken_user_was_loggedOut() {
        TokenRequestDTO tokenRequest = new TokenRequestDTO();
        tokenRequest.setFacebook_token("token");
        tokenRequest.setDevice_id("device_id");
        FacebookResponse facebookResponse = new FacebookResponse();
        facebookResponse.setAge(20L);
        when(facebookService.getProfileNameAndId(anyString()))
                .thenReturn(Optional.of(facebookResponse));
        when(facebookService.getProfilePictureUrl(anyString())).thenReturn(Optional.empty());
        when(inUserRepository.save(any(InUser.class))).thenReturn(null);
        when(inUserLoginRepository.saveAndFlush(any(InUserLogin.class))).thenReturn(null);
        when(inUserFacebookRepository.save(any(InUserFacebook.class))).thenReturn(null);
        InUserFacebook inUserFacebook = new InUserFacebook();
        InUser inUser = new InUser();
        inUser.setHeight(160F);
        inUser.setWeight(60F);
        inUserFacebook.setInUser(inUser);
        InUserLogin inUserLogin = new InUserLogin();
        inUser.setInUserLogins(Arrays.asList(inUserLogin));
        InUserLogout inUserLogout = new InUserLogout();
        inUser.setInUserLogouts(Arrays.asList(inUserLogout));
        when(inUserFacebookRepository.findByDeviceId(anyString()))
                .thenReturn(Arrays.asList(inUserFacebook));
        TokenResponseDTO tokenResponseDTO = tokenService.createOrReadNewToken(tokenRequest, "");
        TokenResponseDTO tokenResponseDTO2 = tokenService.createOrReadNewToken(tokenRequest, "");
        assertThat(tokenResponseDTO.getToken(), equalTo(tokenResponseDTO2.getToken()));
        verify(facebookService, times(2))
                .getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService, times(2))
                .getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
        verify(inUserRepository, times(2)).save(any(InUser.class));
        verify(inUserLoginRepository, times(2)).saveAndFlush(any(InUserLogin.class));
        verify(inUserFacebookRepository, times(2)).save(any(InUserFacebook.class));
    }

    @Test
    public void createOrReadNewToken_user_was_loggedOut2() {
        TokenRequestDTO tokenRequest = new TokenRequestDTO();
        tokenRequest.setFacebook_token("token");
        tokenRequest.setDevice_id("device_id");
        FacebookResponse facebookResponse = new FacebookResponse();
        facebookResponse.setAge(20L);
        when(facebookService.getProfileNameAndId(anyString()))
                .thenReturn(Optional.of(facebookResponse));
        when(facebookService.getProfilePictureUrl(anyString())).thenReturn(Optional.empty());
        when(inUserRepository.save(any(InUser.class))).thenReturn(null);
        when(inUserLoginRepository.saveAndFlush(any(InUserLogin.class))).thenReturn(null);
        when(inUserFacebookRepository.save(any(InUserFacebook.class))).thenReturn(null);
        InUserFacebook inUserFacebook = new InUserFacebook();
        InUser inUser = new InUser();
        inUserFacebook.setInUser(inUser);
        InUserLogin inUserLogin = new InUserLogin();
        inUser.setInUserLogins(Arrays.asList(inUserLogin));
        InUserLogout inUserLogout = new InUserLogout();
        inUser.setInUserLogouts(Arrays.asList(inUserLogout));
        when(inUserFacebookRepository.findByDeviceId(anyString()))
                .thenReturn(Arrays.asList(inUserFacebook));
        when(inUserLogoutRepository.findByToken(anyString()))
                .thenReturn(Arrays.asList(new InUserLogout()));
        tokenService.createOrReadNewToken(tokenRequest, "");
        tokenService.createOrReadNewToken(tokenRequest, "");
        verify(facebookService, times(2))
                .getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService, times(2))
                .getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
        verify(inUserRepository, times(2)).save(any(InUser.class));
        verify(inUserLoginRepository, times(2)).saveAndFlush(any(InUserLogin.class));
        verify(inUserFacebookRepository, times(2)).save(any(InUserFacebook.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteToken_not_found() {
        tokenService.deleteToken("", "");
    }

    @Test
    public void deleteToken_success() {
        when(inUserLoginRepository.findByToken(anyString()))
                .thenReturn(Arrays.asList(new InUserLogin()));
        when(inUserLogoutRepository.saveAndFlush(any(InUserLogout.class))).thenReturn(null);
        tokenService.deleteToken("", "");
        verify(inUserLogoutRepository).saveAndFlush(any(InUserLogout.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteToken_invalid_token() {
        when(inUserLoginRepository.findByToken(anyString()))
                .thenReturn(Arrays.asList(new InUserLogin()));
        when(inUserLogoutRepository.findByToken(anyString()))
                .thenReturn(Arrays.asList(new InUserLogout()));
        tokenService.deleteToken("", "");
        verify(inUserLogoutRepository).saveAndFlush(any(InUserLogout.class));
    }
}
