package com.osomapps.pt.admin.program;

import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWarmupWorkoutItemRepository;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutRepository;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserFacebook;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import com.osomapps.pt.programs.ParseGoalRepository;
import com.osomapps.pt.reportworkout.InWorkoutItemSetRepository;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramAssignServiceTest {

    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InProgramRepository inProgramRepository;
    @Mock
    private InWorkoutRepository inWorkoutRepository;
    @Mock
    private InWorkoutItemRepository inWorkoutItemRepository;
    @Mock
    private InWorkoutItemSetRepository inWorkoutItemSetRepository;
    @Mock
    private ParseGoalRepository parseUserRepository;
    @Mock
    private InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository;
    @Mock
    private AdminProgramScanExerciseService adminProgramScanExerciseService;
    @InjectMocks
    private AdminProgramAssignService adminProgramAssignService;

    @Test
    @SuppressWarnings("unchecked")
    public void assign() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseUserRepository.save(any(Iterable.class))).thenAnswer(i -> i.getArguments()[0]);
        adminProgramAssignService.assign(new InUser());
        verify(inUserRepository).save(any(InUser.class));
    }

}
