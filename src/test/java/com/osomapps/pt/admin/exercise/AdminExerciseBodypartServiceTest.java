package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.ExerciseBodypart;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseBodypartServiceTest {

    @Mock
    private ExerciseBodypartRepository exerciseBodypartRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private AdminExerciseBodypartService adminExerciseBodypartService;

    @Test
    public void findAll() {
        when(exerciseBodypartRepository.findAll()).thenReturn(Arrays.asList(new ExerciseBodypart()));
        List<ExerciseBodypartResponseDTO> responseDTOs = adminExerciseBodypartService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

}
