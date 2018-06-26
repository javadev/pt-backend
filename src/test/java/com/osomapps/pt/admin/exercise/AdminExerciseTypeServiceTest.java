package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseType;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminExerciseTypeServiceTest {

    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;
    @InjectMocks
    private AdminExerciseTypeService adminExerciseTypeService;

    @Test
    public void findAll() {
        when(exerciseTypeRepository.findAll()).thenReturn(Arrays.asList(new ExerciseType()));
        List<ExerciseTypeResponseDTO> responseDTOs = adminExerciseTypeService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }
}
