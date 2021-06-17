package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import com.osomapps.pt.exercises.ExerciseOutput;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseOutputServiceTest {

    @Mock private ExerciseOutputRepository exerciseOutputRepository;
    @InjectMocks private AdminExerciseOutputService adminExerciseOutputService;

    @Test
    public void findAll() {
        when(exerciseOutputRepository.findAll(any(Sort.class)))
                .thenReturn(Arrays.asList(new ExerciseOutput()));
        List<ExerciseOutputResponseDTO> responseDTOs = adminExerciseOutputService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }
}
