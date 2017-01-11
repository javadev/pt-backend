package com.osomapps.pt.admin.goal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminGoalTypeResourceTest {

    @Mock
    private AdminGoalTypeService adminGoalTypeService;

    @InjectMocks
    private AdminGoalTypeResource adminGoalTypeResource;

    @Test
    public void findAll() {
        adminGoalTypeResource.findAll();
        verify(adminGoalTypeService).findAll();
    }

}
