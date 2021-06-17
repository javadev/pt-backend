package com.osomapps.pt.admin.goal;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.goals.GoalParameter;
import com.osomapps.pt.goals.GoalParameterRepository;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminGoalParameterServiceTest {

    @Mock private GoalParameterRepository goalParameterRepository;

    @InjectMocks private AdminGoalParameterService adminGoalParameterService;

    @Test
    public void findAll() {
        when(goalParameterRepository.findAll()).thenReturn(Arrays.asList(new GoalParameter()));
        adminGoalParameterService.findAll();
        verify(goalParameterRepository).findAll();
    }
}
