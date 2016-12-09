package com.osomapps.pt.admin.ptuser;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminPtUserResourceTest {

    @Mock
    private AdminPtUserService adminPtUserService;

    @InjectMocks
    private AdminPtUserResource adminPtUserResource;

    @Test
    public void findAll() {
        adminPtUserResource.findAll();
        verify(adminPtUserService).findAll();
    }

    @Test
    public void findOne() {
        adminPtUserResource.findOne(1L);
        verify(adminPtUserService).findOne(eq(1L));
    }

    @Test
    public void create() {
        adminPtUserResource.create(new PtUserRequestDTO());
        verify(adminPtUserService).create(any(PtUserRequestDTO.class));
    }

    @Test
    public void update() {
        adminPtUserResource.update(1L, new PtUserRequestDTO());
        verify(adminPtUserService).update(eq(1L), any(PtUserRequestDTO.class));
    }

    @Test
    public void delete() throws Exception {
        adminPtUserResource.delete(1L);
        verify(adminPtUserService).delete(anyLong());
    }
}
