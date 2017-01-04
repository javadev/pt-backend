package com.osomapps.pt.goals;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.user.UserService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

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

}
