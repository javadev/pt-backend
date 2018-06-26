package com.osomapps.pt.goals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class GoalsResourceTest {

    @Mock
    private GoalService goalService;    

    @InjectMocks
    private GoalsResource goalsResource;

    @Test
    public void findAll() {
        goalsResource.findAll();
        verify(goalService).findAll();
    }
}

