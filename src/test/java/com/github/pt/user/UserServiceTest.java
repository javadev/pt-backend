package com.github.pt.user;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.token.InUserLoginRepository;
import com.github.pt.token.InUserLogoutRepository;
import com.github.pt.token.InUserRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
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

    @Test(expected = ResourceNotFoundException.class)
    public void updateUser() {
        userService.updateUser("1", new UserRequestDTO());
    }
}
