package com.osomapps.pt.admin.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
