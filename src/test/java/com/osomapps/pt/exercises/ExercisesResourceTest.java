package com.osomapps.pt.exercises;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExercisesResourceTest {

    @Mock private ExerciseService exerciseService;

    @InjectMocks private ExercisesResource exercisesResource;

    @Test
    public void findAll() throws Exception {
        exercisesResource.findAll();
        verify(exerciseService).findAll();
    }
}
