package com.osomapps.pt.admin.exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
