package com.osomapps.pt.admin.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserLevelResourceTest {

    @Mock
    private AdminUserLevelService userLevelService;
    @InjectMocks
    private AdminUserLevelResource adminUserLevelResource;

    @Test
    public void findAll() {
        adminUserLevelResource.findAll();
        verify(userLevelService).findAll();
    }
}
