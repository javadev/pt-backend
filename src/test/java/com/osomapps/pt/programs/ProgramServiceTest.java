package com.osomapps.pt.programs;

import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProgramServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private ProgramService programService;

    @Test
    public void getPredefinedPrograms() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        InWorkout inWorkout = new InWorkout()
                .setInWarmupWorkoutItems(null)
                .setInWorkoutItems(Arrays.asList(new InWorkoutItem()
                    .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()))));
        InProgram inProgram = new InProgram()
                .setId(1L)
                .setName("name")
                .setInWorkouts(Arrays.asList(inWorkout));
        inUserForLogin.setInPrograms(Arrays.asList(inProgram));
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        programService.getPredefinedPrograms("1");
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void getExamples() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        InWorkout inWorkout = new InWorkout()
                .setInWarmupWorkoutItems(null)
                .setInWorkoutItems(Arrays.asList(new InWorkoutItem()
                    .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()))));
        InProgram inProgram = new InProgram()
                .setId(1L)
                .setName("name")
                .setInWorkouts(Arrays.asList(inWorkout));
        inUserForLogin.setInPrograms(Arrays.asList(inProgram));
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        programService.getExamples("1");
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void getExamples_with_duplicates() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        InWorkout inWorkout = new InWorkout()
                .setInWarmupWorkoutItems(Arrays.asList(new InWarmupWorkoutItem()))
                .setInWorkoutItems(Arrays.asList(new InWorkoutItem()
                    .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()))));
        InProgram inProgram1 = new InProgram()
                .setId(1L)
                .setName("name")
                .setInWorkouts(Arrays.asList(inWorkout));
        InProgram inProgram2 = new InProgram()
                .setId(2L)
                .setName("name")
                .setInWorkouts(Arrays.asList(inWorkout));
        inUserForLogin.setInPrograms(Arrays.asList(inProgram1, inProgram2));
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        List<ProgramResponseDTO> responses = programService.getExamples("1");
        verify(userService).checkUserToken(eq("1"));
        assertThat(responses.size(), equalTo(1));
        assertThat(responses.get(0).getId(), equalTo(2L));
    }

    @Test
    public void getExamples_with_empty_token() {
        List<ProgramResponseDTO> responses = programService.getExamples("");
        assertThat(responses.size(), equalTo(0));
    }

    @Test
    public void getExamples_with_exercise_id_and_type() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        InWorkout inWorkout = new InWorkout()
                .setInWarmupWorkoutItems(Arrays.asList(new InWarmupWorkoutItem().setD_exercise_id("1")))
                .setInWorkoutItems(Arrays.asList(new InWorkoutItem()
                        .setD_exercise_id("1").setD_exercise_type("T")
                        .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()))));
        InProgram inProgram = new InProgram()
                .setId(1L)
                .setName("name")
                .setInWorkouts(Arrays.asList(inWorkout));
        inUserForLogin.setInPrograms(Arrays.asList(inProgram));
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        programService.getExamples("1");
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void getExamples_with_empty_programs() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserForLogin.setInPrograms(Collections.emptyList());
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        programService.getExamples("1");
        verify(userService).checkUserToken(eq("1"));
    }
}
