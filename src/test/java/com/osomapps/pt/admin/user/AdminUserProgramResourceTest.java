package com.osomapps.pt.admin.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserProgramResourceTest {

    @Mock
    private AdminUserProgramService userProgramService;
    @InjectMocks
    private AdminUserProgramResource adminUserProgramResource;

    @Test
    public void findAll() {
        adminUserProgramResource.findAll();
        verify(userProgramService).findAll();
    }

    @Test
    public void findOne() {
        adminUserProgramResource.findOne(1L);
        verify(userProgramService).findOne(eq(1L));
    }

    @Test
    public void create() {
        adminUserProgramResource.create(new UserProgramRequestDTO());
        verify(userProgramService).create(any(UserProgramRequestDTO.class));
    }

    @Test
    public void update() {
        adminUserProgramResource.update(1L, new UserProgramRequestDTO());
        verify(userProgramService).update(eq(1L), any(UserProgramRequestDTO.class));
    }

    @Test
    public void delete() {
        adminUserProgramResource.delete(1L);
        verify(userProgramService).delete(anyLong());
    }

}
