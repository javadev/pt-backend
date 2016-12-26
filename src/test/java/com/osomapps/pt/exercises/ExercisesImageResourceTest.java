package com.osomapps.pt.exercises;

import java.io.OutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletResponse;

@RunWith(MockitoJUnitRunner.class)
public class ExercisesImageResourceTest {

    @Mock
    private ExerciseImageService exerciseImageService;    

    @InjectMocks
    private ExercisesImageResource exercisesImageResource;

    @Test
    public void findAll() throws Exception {
        when(exerciseImageService.findOne(eq(1L), eq(""), any(OutputStream.class)))
                .thenReturn(new ExerciseImageDTO());
        exercisesImageResource.findOne(1L, "", new MockHttpServletResponse());
        verify(exerciseImageService).findOne(eq(1L), eq(""), any(OutputStream.class));
    }

}
