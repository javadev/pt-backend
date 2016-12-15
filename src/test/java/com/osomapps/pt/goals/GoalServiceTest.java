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
    @Mock
    private UserService userService;
    @InjectMocks
    private GoalService goalService;

    @Test
    public void foindAll_empty_token() {
        goalService.findAll("");
        verify(goalRepository).findAll();
    }

    @Test
    public void foindAll() {
        when(goalRepository.findAll()).thenReturn(Arrays.asList(
                new Goal().setGoalParameters(Arrays.asList(new GoalParameter()))));
        goalService.findAll("1");
        verify(goalRepository).findAll();
    }

}
