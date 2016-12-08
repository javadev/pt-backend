package com.github.pt.admin.exercise;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseOutputResourceTest {
    @Mock
    private AdminExerciseOutputService exerciseOutputService;
    @InjectMocks
    private AdminExerciseOutputResource adminExerciseOutputResource;

    @Test
    public void findAll() {
        adminExerciseOutputResource.findAll();
        verify(exerciseOutputService).findAll();
    }
}
