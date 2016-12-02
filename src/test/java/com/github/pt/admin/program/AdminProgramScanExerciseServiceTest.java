package com.github.pt.admin.program;

import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.Exercise;
import com.github.pt.exercises.ExerciseRepository;
import java.util.Arrays;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramScanExerciseServiceTest {
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private DictionaryService dictionaryService;    

    @InjectMocks
    private AdminProgramScanExerciseService adminProgramScanExerciseService;

    @Test
    public void getExerciseIdByName() {
        when(exerciseRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(
                new Exercise().setDExerciseName("10").setExercise_id(10L)));
        when(dictionaryService.getEnValue(eq(DictionaryName.exercise_name), eq("10"), eq(""))).thenReturn("test");
        Optional<Long> result = adminProgramScanExerciseService.getExerciseIdByName("test");
        Assert.assertThat(result.isPresent(), equalTo(true));
        Assert.assertThat(result.get(), equalTo(10L));
    }

}
