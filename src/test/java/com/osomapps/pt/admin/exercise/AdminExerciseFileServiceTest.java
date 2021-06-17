package com.osomapps.pt.admin.exercise;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.exercises.ExerciseFile;
import com.osomapps.pt.exercises.ExerciseFileRepository;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseFileServiceTest {

    @Mock private ExerciseFileRepository exerciseFileRepository;

    @InjectMocks private AdminExerciseFileService adminExerciseFileService;

    @Test
    public void findOne() {
        when(exerciseFileRepository.findAllById(eq(Arrays.asList(1L))))
                .thenReturn(Arrays.asList(new ExerciseFile()));
        adminExerciseFileService.findAll(Arrays.asList(1L));
        verify(exerciseFileRepository).findAllById(eq(Arrays.asList(1L)));
    }
}
