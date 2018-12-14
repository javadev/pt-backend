package com.osomapps.pt.exercises;

import com.osomapps.pt.dictionary.DictionaryService;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceTest {
    @Mock
    private ExerciseViewRepository exerciseViewRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private ExerciseService exerciseService;
    
    @Test
    public void findAll() {
        when(exerciseViewRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(
                new ExerciseView()
        .setExerciseTypes(Arrays.asList(new ExerciseType()))
        .setExerciseFiles(Arrays.asList(new ExerciseFileView()
                .setId(1L)
                .setFile_name("test")))
        ));
        List<ExerciseDTO> exerciseDTOs = exerciseService.findAll();
        verify(exerciseViewRepository).findAll(any(Sort.class));
        assertThat(exerciseDTOs.size(), equalTo(1));
        assertThat(exerciseDTOs.get(0).getImages().get(0), equalTo("/api/v1/exercise-image/1/test"));
    }
}
