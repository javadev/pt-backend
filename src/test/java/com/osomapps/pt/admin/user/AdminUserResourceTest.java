package com.osomapps.pt.admin.user;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserResourceTest {

    @Mock private AdminUserService adminUserService;

    @InjectMocks private AdminUserResource adminUserResource;

    @Test
    public void findOne() {
        adminUserResource.findOne(1L);
        verify(adminUserService).findOne(eq(1L));
    }

    @Test
    public void create() {
        adminUserResource.create(new UserRequestDTO());
        verify(adminUserService).create(any(UserRequestDTO.class));
    }

    @Test
    public void update() {
        adminUserResource.update(1L, new UserRequestDTO());
        verify(adminUserService).update(eq(1L), any(UserRequestDTO.class));
    }

    @Test
    public void delete() {
        adminUserResource.delete(1L);
        verify(adminUserService).delete(anyLong());
    }
}
