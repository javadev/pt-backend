package com.osomapps.pt.admin.goal;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminGoalParameterResourceTest {

    @Mock private AdminGoalParameterService adminGoalParameterService;

    @InjectMocks private AdminGoalParameterResource adminGoalParameterResource;

    @Test
    public void findAll() {
        adminGoalParameterResource.findAll();
        verify(adminGoalParameterService).findAll();
    }
}
