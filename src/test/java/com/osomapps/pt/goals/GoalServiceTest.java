package com.osomapps.pt.goals;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
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
    public void testSomeMethod() {
        goalService.findAll("1");
        verify(goalRepository).findAll();
    }

}
