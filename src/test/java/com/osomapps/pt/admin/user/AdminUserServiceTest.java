package com.osomapps.pt.admin.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.admin.program.AdminProgramAssignService;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalRepository;
import com.osomapps.pt.goals.InUserGoalRepository;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import com.osomapps.pt.programs.InWorkoutItemSet;
import com.osomapps.pt.programs.InWorkoutItemSetReport;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserFacebook;
import com.osomapps.pt.token.InUserFacebookRepository;
import com.osomapps.pt.token.InUserGoal;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.tokenemail.EmailValidator;
import com.osomapps.pt.tokenemail.InUserEmail;
import com.osomapps.pt.tokenemail.InUserEmailRepository;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminUserServiceTest {

    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InUserEmailRepository inUserEmailRepository;
    @Mock
    private InUserFacebookRepository inUserFacebookRepository;
    @Mock
    private InUserTypeRepository inUserTypeRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private EmailValidator emailValidator;
    @Mock
    private DictionaryService dictionaryService;
    @Mock
    private InUserGoalRepository inUserGoalRepository;
    @Mock
    private GoalRepository goalRepository;
    @Mock
    private AdminProgramAssignService adminProgramAssignService;

    @InjectMocks
    private AdminUserService adminUserService;

    @Test
    public void findAll() {
        when(inUserRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(
                new InUser()
                    .setInUserEmails(Collections.emptyList())));
        adminUserService.findAll();
        verify(inUserRepository).findAll(any(Sort.class));
    }

    @Test
    public void findOne_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserService.findOne(1L);});
    }

    private InProgram getInProgram() {
        return new InProgram()
        .setInWorkouts(Arrays.asList(
                new InWorkout()
                    .setInWarmupWorkoutItems(Arrays.asList(new InWarmupWorkoutItem()
                    ))
                    .setInWorkoutItems(Arrays.asList(
                            new InWorkoutItem()
                    .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()))
                    .setInWorkoutItemReports(Arrays.asList(
                            new InWorkoutItemReport()
                                    .setInWorkoutItemSetReports(Arrays.asList(
                                            new InWorkoutItemSetReport()))
                    ))))
        ));
    }

    @Test
    public void findOne() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
        .setInUserEmails(Collections.emptyList())
        .setInUserType(new InUserType())
        .setInPrograms(Arrays.asList(getInProgram())));
        UserResponseDTO userResponseDTO = adminUserService.findOne(1L);
        assertThat(userResponseDTO.getName(), equalTo("?"));
    }

    @Test
    public void create() {
        when(adminProgramAssignService.assign(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(goalRepository.findOne(eq(1L))).thenReturn(new Goal().setId(1L));
        when(inUserGoalRepository.save(any(InUserGoal.class))).thenAnswer(i -> i.getArguments()[0]);
        UserResponseDTO userResponseDTO = adminUserService.create(
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
                .setLevel(1)
                .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L)))
        );
        assertThat(userResponseDTO.getName(), equalTo(null));
    }

    @Test
    public void create_emailValidator() {
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("email", "Invalid empty email");
            return null;
        }).when(emailValidator).validate(anyObject(), any(Errors.class));
        assertThrows(UnauthorizedException.class, () -> {adminUserService.create(
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );});
    }

    @Test
    public void update_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserService.update(1L,new UserRequestDTO().setType(new UserTypeRequestDTO()));});
    }

    @Test
    public void update() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
            .setInUserEmails(Collections.emptyList())
            .setInUserFacebooks(Arrays.asList(new InUserFacebook())));
        when(adminProgramAssignService.assign(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(goalRepository.findOne(eq(1L))).thenReturn(new Goal().setId(1L));
        when(inUserGoalRepository.save(any(InUserGoal.class))).thenAnswer(i -> i.getArguments()[0]);
        UserResponseDTO userResponseDTO = adminUserService.update(1L,
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
                .setLevel(2)
                .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L)))
        );
        assertThat(userResponseDTO.getName(), equalTo(null));
    }

    @Test
    public void update_with_eamil() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
            .setInUserEmails(Arrays.asList(new InUserEmail()))
            .setInUserFacebooks(Arrays.asList(new InUserFacebook())));
        when(adminProgramAssignService.assign(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        UserResponseDTO userResponseDTO = adminUserService.update(1L,
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );
        assertThat(userResponseDTO.getName(), equalTo(null));
    }

    @Test
    public void update_with_invalid_eamil() {
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("emailValidator", "emailValidator");
            return null;
        }).when(emailValidator).validate(anyObject(), any(Errors.class));
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
            .setInUserEmails(Arrays.asList(new InUserEmail()))
            .setInUserFacebooks(Arrays.asList(new InUserFacebook())));
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        assertThrows(UnauthorizedException.class, () -> {adminUserService.update(1L,
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );});
    }

    @Test
    public void delete_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserService.delete(1L);});
    }

    @Test
    public void delete() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
            .setInUserEmails(Arrays.asList(new InUserEmail())));
        UserResponseDTO userResponseDTO = adminUserService.delete(1L);
        assertThat(userResponseDTO.getName(), equalTo(null));
    }
}
