package com.osomapps.pt.exercises;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ExercisesResourceTest {

    @Mock
    private ExerciseService exerciseService;    

    @InjectMocks
    private ExercisesResource exercisesResource;

    @Test
    public void findAll() throws Exception {
        exercisesResource.findAll();
        verify(exerciseService).findAll();
    }
}
