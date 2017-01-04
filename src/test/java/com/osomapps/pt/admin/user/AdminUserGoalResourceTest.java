package com.osomapps.pt.admin.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserGoalResourceTest {

    @Mock
    private AdminUserGoalService userGoalService;
    @InjectMocks
    private AdminUserGoalResource adminUserGoalResource;

    @Test
    public void findAll() {
        adminUserGoalResource.findAll();
        verify(userGoalService).findAll();
    }
}
