package com.osomapps.pt.exercises;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.ResourceNotFoundException;
import java.io.ByteArrayOutputStream;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseImageServiceTest {

    @Mock private ExerciseFileRepository exerciseFileRepository;

    @InjectMocks private ExerciseImageService exerciseImageService;

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() throws Exception {
        exerciseImageService.findOne(1L, "", new ByteArrayOutputStream());
    }

    @Test
    public void findOne() throws Exception {
        when(exerciseFileRepository.findById(eq(1L)))
                .thenReturn(
                        Optional.of(
                                new ExerciseFile()
                                        .setData_url(
                                                "data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                                                        + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                                                        + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                                                        + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7")));
        exerciseImageService.findOne(1L, "", new ByteArrayOutputStream());
        verify(exerciseFileRepository).findById(eq(1L));
    }
}
