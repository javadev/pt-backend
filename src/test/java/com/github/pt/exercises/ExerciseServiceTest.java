package com.github.pt.exercises;

import com.github.pt.dictionary.DictionaryService;
import com.github.pt.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
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
        exerciseService.findAll("1");
        verify(exerciseRepository).findAll();
    }
}
