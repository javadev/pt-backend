package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.ExerciseBodypart;
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
