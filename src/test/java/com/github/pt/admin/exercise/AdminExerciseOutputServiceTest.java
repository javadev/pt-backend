package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseOutput;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseOutputServiceTest {

    @Mock
    private ExerciseOutputRepository exerciseOutputRepository;
    @InjectMocks
    private AdminExerciseOutputService adminExerciseOutputService;

    @Test
    public void findAll() {
        when(exerciseOutputRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(new ExerciseOutput()));
        List<ExerciseOutputResponseDTO> responseDTOs = adminExerciseOutputService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }
}
