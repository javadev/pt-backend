package com.osomapps.pt.admin.goal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
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
