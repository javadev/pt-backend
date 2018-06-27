package com.osomapps.pt.admin.goal;

import com.osomapps.pt.admin.certificate.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminGoalResourceTest {

    @Mock
    private AdminGoalService adminGoalService;

    @InjectMocks
    private AdminGoalResource adminGoalResource;

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
