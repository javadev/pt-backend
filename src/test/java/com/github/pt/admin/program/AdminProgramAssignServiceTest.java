package com.github.pt.admin.program;

import com.github.pt.programs.InProgramRepository;
import com.github.pt.programs.InWarmupWorkoutItemRepository;
import com.github.pt.programs.InWorkoutItem;
import com.github.pt.programs.InWorkoutRepository;
import com.github.pt.programs.ParseUser;
import com.github.pt.programs.ParseUserRepository;
import com.github.pt.programs.ParseWarmupWorkoutItem;
import com.github.pt.programs.ParseWorkout;
import com.github.pt.programs.ParseWorkoutItem;
import com.github.pt.programs.Program;
import com.github.pt.reportworkout.InWorkoutItemRepository;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserFacebook;
import com.github.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

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
    private ParseUserRepository parseUserRepository;
    @Mock
    private InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository;
    @Mock
    private AdminProgramScanExerciseService adminProgramScanExerciseService;
    @InjectMocks
    private AdminProgramAssignService adminProgramAssignService;

    @Test
    public void assign() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseUserRepository.save(anyList())).thenAnswer(i -> i.getArguments()[0]);
        adminProgramAssignService.assign(Arrays.asList(new ParseUser()
                .setName("user_name")
                .setProgram(new Program().setName("program name"))
                .setParseWorkouts(Arrays.asList(
                        new ParseWorkout()
                .setParseWarmupWorkoutItems(Arrays.asList(
                        new ParseWarmupWorkoutItem()))
                .setParseWorkoutItems(Arrays.asList(
                        new ParseWorkoutItem()))))
        ));
        verify(parseUserRepository).save(anyList());
    }

}
