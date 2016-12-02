package com.github.pt.programs;

import com.github.pt.token.InUser;
import com.github.pt.token.InUserLogin;
import com.github.pt.user.UserService;
import java.util.Arrays;
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
    public void getExamples() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        InWorkout inWorkout = new InWorkout().setInWorkoutItems(Arrays.asList(new InWorkoutItem()));
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
}
