package com.osomapps.pt.admin.user;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalRepository;
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
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserGoalServiceTest {
    @Mock
    private GoalRepository goalRepository;
    @Mock
    private DictionaryService dictionaryService;    
    
    @InjectMocks
    private AdminUserGoalService adminUserGoalService;

    @Test
    public void findOne() {
        when(goalRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(new Goal()));
        List<UserGoalResponseDTO> goalResponseDTOs = adminUserGoalService.findAll();
        verify(goalRepository).findAll(any(Sort.class));
        assertThat(goalResponseDTOs.size(), equalTo(1));
    }

}
