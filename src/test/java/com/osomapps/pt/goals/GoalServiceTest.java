package com.osomapps.pt.goals;

import com.osomapps.pt.dictionary.DictionaryService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GoalServiceTest {

    @Mock
    private GoalRepository goalRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private GoalService goalService;

    @Test
    public void findAll_empty_token() {
        goalService.findAll();
        verify(goalRepository).findAll();
    }

    @Test
    public void findAll() {
        when(goalRepository.findAll()).thenReturn(Arrays.asList(
                new Goal().setGoalParameters(Arrays.asList(new GoalParameter()))));
        goalService.findAll();
        verify(goalRepository).findAll();
    }

    @Test
    public void findAll_with_type() {
        when(goalRepository.findAll()).thenReturn(Arrays.asList(
                new Goal().setGoalType(new GoalType())
                        .setGoalParameters(Arrays.asList(new GoalParameter()))));
        goalService.findAll();
        verify(goalRepository).findAll();
    }
}
