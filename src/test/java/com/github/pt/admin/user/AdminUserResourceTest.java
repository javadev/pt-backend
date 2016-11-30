package com.github.pt.admin.user;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserResourceTest {

    @Mock
    private AdminUserService adminUserService;    
    
    @InjectMocks
    private AdminUserResource adminUserResource;

    @Test
    public void create() {
        adminUserResource.create(new UserRequestDTO());
        verify(adminUserService).create(any(UserRequestDTO.class));
    }

    public void delete() throws Exception {
        adminUserResource.delete(1L);
        verify(adminUserService).delete(anyLong());
    }
}
