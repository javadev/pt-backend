package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.osomapps.pt.exercises.ExerciseType;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseTypeServiceTest {

    @Mock private ExerciseTypeRepository exerciseTypeRepository;
    @InjectMocks private AdminExerciseTypeService adminExerciseTypeService;

    @Test
    public void findAll() {
        when(exerciseTypeRepository.findAll()).thenReturn(Arrays.asList(new ExerciseType()));
        List<ExerciseTypeResponseDTO> responseDTOs = adminExerciseTypeService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }
}
