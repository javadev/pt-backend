package com.osomapps.pt.admin.goal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminGoalParameterResourceTest {

    @Mock
    private AdminGoalParameterService adminGoalParameterService;

    @InjectMocks
    private AdminGoalParameterResource adminGoalParameterResource;

    @Test
    public void findAll() {
        adminGoalParameterResource.findAll();
        verify(adminGoalParameterService).findAll();
    }

}
