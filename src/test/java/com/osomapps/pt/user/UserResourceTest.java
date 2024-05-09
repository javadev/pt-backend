package com.osomapps.pt.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {

    @Mock private UserService userService;

    @InjectMocks private UserResource userResource;

    @Test
    public void findOne() {
        userResource.findOne("1");
        verify(userService).findOne(eq("1"));
    }

    @Test
    public void update() {
        userResource.update("1", new UserRequestDTO());
        verify(userService).updateUser(eq("1"), any(UserRequestDTO.class));
    }
}
