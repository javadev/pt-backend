package com.osomapps.pt.admin.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
