package com.osomapps.pt.admin.goal;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalParameter;
import com.osomapps.pt.goals.GoalParameterRepository;
import com.osomapps.pt.goals.GoalRepository;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminGoalServiceTest {

    @Mock
    private GoalRepository goalRepository;
    @Mock
    private GoalParameterRepository goalParameterRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private AdminGoalService adminGoalService;

    @Test
    public void findAll() {
        when(goalRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(
                new Goal().setGoalParameters(Arrays.asList(new GoalParameter()))));
        List<GoalResponseDTO> goalResponseDTOs = adminGoalService.findAll();
        assertThat(goalResponseDTOs.size(), equalTo(1));
    }

}
