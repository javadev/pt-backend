package com.osomapps.pt.admin.goal;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminGoalTypeResourceTest {

    @Mock private AdminGoalTypeService adminGoalTypeService;

    @InjectMocks private AdminGoalTypeResource adminGoalTypeResource;

    @Test
    public void findAll() {
        adminGoalTypeResource.findAll();
        verify(adminGoalTypeService).findAll();
    }
}
