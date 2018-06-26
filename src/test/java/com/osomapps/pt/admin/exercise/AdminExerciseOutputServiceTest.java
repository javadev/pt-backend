package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseOutput;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

@ExtendWith(MockitoExtension.class)
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
