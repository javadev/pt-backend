package com.github.pt.reportworkout;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import com.github.pt.programs.InProgram;
import com.github.pt.programs.InWorkout;
import com.github.pt.programs.InWorkoutItem;
import com.github.pt.programs.InWorkoutItemReport;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserLogin;
import com.github.pt.user.UserService;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportWorkoutServiceTest {

    @Mock
    private InWorkoutItemRepository inWorkoutItemRepository;
    @Mock
    private InWorkoutItemReportRepository inWorkoutItemReportRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private ReportWorkoutService reportWorkoutService;

    @Test
    public void findAll() {
        reportWorkoutService.findAll("1");
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        WorkoutItemReportRequestDTO workoutItemReportRequestDTO = new WorkoutItemReportRequestDTO();
        workoutItemReportRequestDTO.setId(1L);
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO));
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        InWorkout inWorkout = new InWorkout();
        inWorkoutItem.setInWorkout(inWorkout);
        InProgram inProgram = new InProgram();
        inWorkout.setInProgram(inProgram);
        InUser inUser = new InUser();
        inUser.setId(10L);
        inProgram.setInUser(inUser);
        when(inWorkoutItemReportRepository.save(any(InWorkoutItemReport.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inWorkoutItemRepository.findById(eq(1L))).thenReturn(Arrays.asList(inWorkoutItem));
        reportWorkoutService.create("1", workoutReportRequestDTO);
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create_empty_token() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        reportWorkoutService.create("", new WorkoutReportRequestDTO());
        verify(userService, never()).checkUserToken(anyString());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void create_empty_workout_item() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        WorkoutItemReportRequestDTO workoutItemReportRequestDTO = new WorkoutItemReportRequestDTO();
        workoutItemReportRequestDTO.setId(1L);
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO));
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        InWorkout inWorkout = new InWorkout();
        inWorkoutItem.setInWorkout(inWorkout);
        InProgram inProgram = new InProgram();
        inWorkout.setInProgram(inProgram);
        InUser inUser = new InUser();
        inUser.setId(10L);
        inProgram.setInUser(inUser);
        when(inWorkoutItemReportRepository.save(any(InWorkoutItemReport.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inWorkoutItemRepository.findById(eq(1L))).thenReturn(Collections.emptyList());
        reportWorkoutService.create("1", workoutReportRequestDTO);
    }

    @Test(expected = UnauthorizedException.class)
    public void create_different_user_id() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        WorkoutItemReportRequestDTO workoutItemReportRequestDTO = new WorkoutItemReportRequestDTO();
        workoutItemReportRequestDTO.setId(1L);
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO));
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        InWorkout inWorkout = new InWorkout();
        inWorkoutItem.setInWorkout(inWorkout);
        InProgram inProgram = new InProgram();
        inWorkout.setInProgram(inProgram);
        InUser inUser = new InUser();
        inUser.setId(1L);
        inProgram.setInUser(inUser);
        when(inWorkoutItemReportRepository.save(any(InWorkoutItemReport.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inWorkoutItemRepository.findById(eq(1L))).thenReturn(Arrays.asList(inWorkoutItem));
        reportWorkoutService.create("1", workoutReportRequestDTO);
    }
}
