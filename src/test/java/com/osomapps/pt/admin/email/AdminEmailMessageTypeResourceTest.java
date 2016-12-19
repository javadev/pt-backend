package com.osomapps.pt.admin.email;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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
