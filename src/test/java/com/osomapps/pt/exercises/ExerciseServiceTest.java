package com.osomapps.pt.exercises;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.user.UserService;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ExerciseServiceTest {
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private DictionaryService dictionaryService;
    @Mock
    private UserService userService;
    @InjectMocks
    private ExerciseService exerciseService;
    
    @Test
    public void findAll() {
        when(exerciseRepository.findAll()).thenReturn(Arrays.asList(
                new Exercise()
        .setExerciseTypes(Arrays.asList(new ExerciseType()))
        .setExerciseFiles(Arrays.asList(new ExerciseFile()))
        ));
        List<ExerciseDTO> exerciseDTOs = exerciseService.findAll("1");
        verify(exerciseRepository).findAll();
        assertThat(exerciseDTOs.size(), equalTo(1));
        assertThat(exerciseDTOs.get(0).getImages(), equalTo(1));
    }
}
