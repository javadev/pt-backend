package com.osomapps.pt.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogout;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import com.osomapps.pt.tokenemail.NameValidator;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InUserLoginRepository inUserLoginRepository;
    @Mock
    private InUserLogoutRepository inUserLogoutRepository;
    @Mock
    private DataurlValidator dataurlValidator;
    @Mock
    private NameValidator nameValidator;

    @InjectMocks
    private UserService userService;

    @Test(expected = ResourceNotFoundException.class)
    public void findOne() {
        userService.findOne("1");
    }

    @Test
    public void findOne_token_found() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        UserResponseDTO userResponseDTO = userService.findOne("1");
        assertThat(userResponseDTO.age, equalTo(32L));
        assertThat(userResponseDTO.height, equalTo(180L));
        assertThat(userResponseDTO.weight, equalTo(50L));
    }

    @Test(expected = UnauthorizedException.class)
    public void findOne_token_with_logout() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Arrays.asList(new InUserLogout()));
        userService.findOne("1");
    }

    @Test
    public void findOne_token_found_without_data() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(new InUser());
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        UserResponseDTO userResponseDTO = userService.findOne("1");
        assertThat(userResponseDTO.age, equalTo(null));
        assertThat(userResponseDTO.height, equalTo(null));
        assertThat(userResponseDTO.weight, equalTo(null));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void updateUser() {
        userService.updateUser("1", new UserRequestDTO());
    }

    @Test
    public void updateUser_token_found() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser("1", new UserRequestDTO());
        verify(inUserRepository).save(any(InUser.class));
    }

    @Test
    public void updateUser_token_found_with_data() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser("1", new UserRequestDTO()
            .setGender("gender")
            .setAge(10L)
            .setHeight(160L)
            .setWeight(60L)
            .setName("Name"));
        verify(inUserRepository).save(any(InUser.class));
    }
}
