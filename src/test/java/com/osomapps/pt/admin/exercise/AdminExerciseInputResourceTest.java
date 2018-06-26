package com.osomapps.pt.admin.exercise;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminExerciseInputResourceTest {
    @Mock
    private AdminExerciseInputService exerciseInputService;
    @InjectMocks
    private AdminExerciseInputResource adminExerciseInputResource;

    @Test
    public void findAll() {
        adminExerciseInputResource.findAll();
        verify(exerciseInputService).findAll();
    }
}
