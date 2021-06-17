package com.osomapps.pt.admin.email;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminEmailMessageTypeResourceTest {

    @Mock private AdminEmailMessageTypeService adminEmailMessageTypeService;

    @InjectMocks private AdminEmailMessageTypeResource adminEmailMessageTypeResource;

    @Test
    public void findAll() {
        adminEmailMessageTypeResource.findAll();
        verify(adminEmailMessageTypeService).findAll();
    }
}
