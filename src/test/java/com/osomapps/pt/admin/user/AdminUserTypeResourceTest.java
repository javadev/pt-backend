package com.osomapps.pt.admin.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserTypeResourceTest {

    @Mock
    private AdminUserTypeService userTypeService;
    @InjectMocks
    private AdminUserTypeResource adminUserTypeResource;

    @Test
    public void findAll() {
        adminUserTypeResource.findAll();
        verify(userTypeService).findAll();
    }

}
