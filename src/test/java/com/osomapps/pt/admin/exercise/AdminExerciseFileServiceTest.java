package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseFile;
import com.osomapps.pt.exercises.ExerciseFileRepository;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminExerciseFileServiceTest {

    @Mock
    private ExerciseFileRepository exerciseFileRepository;    

    @InjectMocks
    private AdminExerciseFileService adminExerciseFileService;

    @Test
    public void findOne() {
        when(exerciseFileRepository.findAll(eq(Arrays.asList(1L)))).thenReturn(
                Arrays.asList(new ExerciseFile()));
        adminExerciseFileService.findAll(Arrays.asList(1L));
        verify(exerciseFileRepository).findAll(eq(Arrays.asList(1L)));
    }
}
