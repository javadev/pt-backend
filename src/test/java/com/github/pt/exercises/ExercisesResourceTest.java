package com.github.pt.exercises;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExercisesResourceTest {

    @Mock
    private ExerciseService exerciseService;    

    @InjectMocks
    private ExercisesResource exercisesResource;

    @Test
    public void findAll() throws Exception {
        exercisesResource.findAll("");
        verify(exerciseService).findAll(eq(""));
    }
}
