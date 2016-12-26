package com.osomapps.pt.exercises;

import java.io.ByteArrayOutputStream;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseImageServiceTest {

    @Mock
    private ExerciseFileRepository exerciseFileRepository;    

    @InjectMocks
    private ExerciseImageService exerciseImageService;

    @Test
    public void findAll() throws Exception {
        when(exerciseFileRepository.findOne(eq(1L))).thenReturn(new ExerciseFile()
                .setData_url("data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
        exerciseImageService.findOne(1L, "", new ByteArrayOutputStream());
        verify(exerciseFileRepository).findOne(eq(1L));
    }
}
