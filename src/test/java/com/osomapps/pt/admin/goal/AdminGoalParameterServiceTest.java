package com.osomapps.pt.admin.goal;

import com.osomapps.pt.goals.GoalParameter;
import com.osomapps.pt.goals.GoalParameterRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminGoalParameterServiceTest {

    @Mock
    private GoalParameterRepository goalParameterRepository;

    @InjectMocks
    private AdminGoalParameterService adminGoalParameterService;

    @Test
    public void findAll() {
        when(goalParameterRepository.findAll()).thenReturn(Arrays.asList(new GoalParameter()));
        adminGoalParameterService.findAll();
        verify(goalParameterRepository).findAll();
    }
}

