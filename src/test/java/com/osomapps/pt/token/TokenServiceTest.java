package com.osomapps.pt.token;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TokenServiceTest {
    
    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InUserLoginRepository inUserLoginRepository;
    @Mock
    private InUserLogoutRepository inUserLogoutRepository;
    @Mock
    private InUserFacebookRepository inUserFacebookRepository;
    @Mock
    private FacebookService facebookService;
    
    @InjectMocks
    private TokenService tokenService;
    
    @Test
    public void createOrReadNewToken_new_registration() {
        TokenRequestDTO tokenRequest = new TokenRequestDTO();
        tokenRequest.setFacebook_token("token");
        tokenRequest.setDevice_id("device_id");
        FacebookResponse facebookResponse = new FacebookResponse();
        facebookResponse.setAge(20L);
        when(facebookService.getProfileNameAndId(anyString())).thenReturn(Optional.of(facebookResponse));
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
        when(facebookService.getProfileNameAndId(anyString())).thenReturn(Optional.of(facebookResponse));
        when(facebookService.getProfilePictureUrl(anyString())).thenReturn(Optional.empty());
        when(inUserRepository.save(any(InUser.class))).thenReturn(null);
        when(inUserLoginRepository.saveAndFlush(any(InUserLogin.class))).thenReturn(null);
        when(inUserFacebookRepository.save(any(InUserFacebook.class))).thenReturn(null);
        InUserFacebook inUserFacebook = new InUserFacebook();
        InUser inUser = new InUser();
        inUserFacebook.setInUser(inUser);
        InUserLogin inUserLogin = new InUserLogin();
        inUser.setInUserLogins(Arrays.asList(inUserLogin));
        when(inUserFacebookRepository.findByTokenAndDeviceId(anyString(), anyString())).thenReturn(
                Arrays.asList(inUserFacebook));
        TokenResponseDTO tokenResponseDTO = tokenService.createOrReadNewToken(tokenRequest, "");
        TokenResponseDTO tokenResponseDTO2 = tokenService.createOrReadNewToken(tokenRequest, "");
        assertThat(tokenResponseDTO.getToken(), equalTo(tokenResponseDTO2.getToken()));
        verify(facebookService, times(2)).getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService, times(2)).getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
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
        when(facebookService.getProfileNameAndId(anyString())).thenReturn(Optional.of(facebookResponse));
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
        when(inUserFacebookRepository.findByTokenAndDeviceId(anyString(), anyString())).thenReturn(
                Arrays.asList(inUserFacebook));
        TokenResponseDTO tokenResponseDTO = tokenService.createOrReadNewToken(tokenRequest, "");
        TokenResponseDTO tokenResponseDTO2 = tokenService.createOrReadNewToken(tokenRequest, "");
        assertThat(tokenResponseDTO.getToken(), equalTo(tokenResponseDTO2.getToken()));
        verify(facebookService, times(2)).getProfileNameAndId(same(tokenRequest.getFacebook_token()));
        verify(facebookService, times(2)).getProfilePictureUrl(same(tokenRequest.getFacebook_token()));
        verify(inUserRepository, times(2)).save(any(InUser.class));
        verify(inUserLoginRepository, times(2)).saveAndFlush(any(InUserLogin.class));
        verify(inUserFacebookRepository, times(2)).save(any(InUserFacebook.class));
    }
    
    @Test(expected = ResourceNotFoundException.class)
    public void deleteToken_not_found() {
        tokenService.deleteToken("", "");
    }

    @Test
    public void deleteToken_success() {
        when(inUserLoginRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogin()));
        when(inUserLogoutRepository.saveAndFlush(any(InUserLogout.class))).thenReturn(null);
        tokenService.deleteToken("", "");
        verify(inUserLogoutRepository).saveAndFlush(any(InUserLogout.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void deleteToken_invalid_token() {
        when(inUserLoginRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogin()));
        when(inUserLogoutRepository.saveAndFlush(any(InUserLogout.class))).thenReturn(null);
        when(inUserLogoutRepository.findByToken(anyString())).thenReturn(Arrays.asList(new InUserLogout()));
        tokenService.deleteToken("", "");
        verify(inUserLogoutRepository).saveAndFlush(any(InUserLogout.class));
    }
}
