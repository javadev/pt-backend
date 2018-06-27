package com.osomapps.pt.admin.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminEmailMessageTypeResourceTest {

    @Mock
    private AdminEmailMessageTypeService adminEmailMessageTypeService;

    @InjectMocks
    private AdminEmailMessageTypeResource adminEmailMessageTypeResource;

    @Test
    public void findAll() {
        adminEmailMessageTypeResource.findAll();
        verify(adminEmailMessageTypeService).findAll();
    }

}
