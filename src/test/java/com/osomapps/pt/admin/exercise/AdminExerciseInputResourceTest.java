package com.osomapps.pt.admin.exercise;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseInputResourceTest {
    @Mock private AdminExerciseInputService exerciseInputService;
    @InjectMocks private AdminExerciseInputResource adminExerciseInputResource;

    @Test
    public void findAll() {
        adminExerciseInputResource.findAll();
        verify(exerciseInputService).findAll();
    }
}
