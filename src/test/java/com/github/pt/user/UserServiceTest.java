package com.github.pt.user;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserLogin;
import com.github.pt.token.InUserLoginRepository;
import com.github.pt.token.InUserLogoutRepository;
import com.github.pt.token.InUserRepository;
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
            .setWeight(60L));
        verify(inUserRepository).save(any(InUser.class));
    }
}
