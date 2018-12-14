package com.osomapps.pt.user;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserResourceTest {

    @Mock
    private UserService userService;    

    @InjectMocks
    private UserResource userResource;

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
