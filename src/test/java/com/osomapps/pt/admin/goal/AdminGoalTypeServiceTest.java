package com.osomapps.pt.admin.goal;

import com.osomapps.pt.goals.GoalType;
import com.osomapps.pt.goals.GoalTypeRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminGoalTypeServiceTest {

    @Mock
    private GoalTypeRepository goalTypeRepository;

    @InjectMocks
    private AdminGoalTypeService adminGoalTypeService;

    @Test
    public void findAll() {
        when(goalTypeRepository.findAll()).thenReturn(Arrays.asList(new GoalType()));
        adminGoalTypeService.findAll();
        verify(goalTypeRepository).findAll();
    }
}
