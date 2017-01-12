package com.osomapps.pt.reportworkout;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import com.osomapps.pt.programs.InWorkoutRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
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
    private InWorkoutRepository inWorkoutRepository;
    @Mock
    private InWorkoutItemRepository inWorkoutItemRepository;
    @Mock
    private InWorkoutItemReportRepository inWorkoutItemReportRepository;
    @Mock
    private InWorkoutItemSetReportRepository inWorkoutItemSetReportRepository;
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
        WorkoutItemSetReportRequestDTO workoutItemSetReportRequestDTO = new WorkoutItemSetReportRequestDTO();
        WorkoutItemReportRequestDTO workoutItemReportRequestDTO = new WorkoutItemReportRequestDTO();
        workoutItemReportRequestDTO.setId(1L);
        workoutItemReportRequestDTO.setSets(Arrays.asList(workoutItemSetReportRequestDTO));
        workoutReportRequestDTO.setId(1L)
                .setItems(Arrays.asList(workoutItemReportRequestDTO));
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
        when(inWorkoutRepository.findOne(eq(1L))).thenReturn(new InWorkout());
        reportWorkoutService.create("1", workoutReportRequestDTO);
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create_with_not_null_weight() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        WorkoutItemSetReportRequestDTO workoutItemSetReportRequestDTO = new WorkoutItemSetReportRequestDTO();
        workoutItemSetReportRequestDTO.setWeight(1);
        WorkoutItemReportRequestDTO workoutItemReportRequestDTO = new WorkoutItemReportRequestDTO();
        workoutItemReportRequestDTO.setId(1L);
        workoutItemReportRequestDTO.setSets(Arrays.asList(workoutItemSetReportRequestDTO));
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO)).setId(1L);
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
        when(inWorkoutRepository.findOne(eq(1L))).thenReturn(new InWorkout());
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
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO)).setId(1L);
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
        when(inWorkoutRepository.findOne(eq(1L))).thenReturn(new InWorkout());
        reportWorkoutService.create("1", workoutReportRequestDTO);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void create_workout_not_found() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        WorkoutItemReportRequestDTO workoutItemReportRequestDTO = new WorkoutItemReportRequestDTO();
        workoutItemReportRequestDTO.setId(1L);
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO)).setId(1L);
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
        when(inWorkoutRepository.findOne(eq(1L))).thenReturn(null);
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
        workoutReportRequestDTO.setItems(Arrays.asList(workoutItemReportRequestDTO)).setId(1L);
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
        when(inWorkoutRepository.findOne(eq(1L))).thenReturn(new InWorkout());
        reportWorkoutService.create("1", workoutReportRequestDTO);
    }
}
