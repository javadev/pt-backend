package com.osomapps.pt.admin.goal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminGoalResourceTest {

    @Mock private AdminGoalService adminGoalService;

    @InjectMocks private AdminGoalResource adminGoalResource;

    @Test
    public void findOne() {
        adminGoalResource.findOne(1L);
        verify(adminGoalService).findOne(anyLong());
    }

    @Test
    public void create() {
        adminGoalResource.create(new GoalRequestDTO());
        verify(adminGoalService).create(any(GoalRequestDTO.class));
    }

    @Test
    public void update() {
        adminGoalResource.update(1L, new GoalRequestDTO());
        verify(adminGoalService).update(anyLong(), any(GoalRequestDTO.class));
    }

    @Test
    public void delete() {
        adminGoalResource.delete(1L);
        verify(adminGoalService).delete(anyLong());
    }
}
