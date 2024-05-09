package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.osomapps.pt.exercises.ExerciseInput;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseInputServiceTest {

    @Mock private ExerciseInputRepository exerciseInputRepository;
    @InjectMocks private AdminExerciseInputService adminExerciseInputService;

    @Test
    public void findAll() {
        when(exerciseInputRepository.findAll(any(Sort.class)))
                .thenReturn(Arrays.asList(new ExerciseInput()));
        List<ExerciseInputResponseDTO> responseDTOs = adminExerciseInputService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }
}
