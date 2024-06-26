package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.admin.program.AdminProgramAssignService;
import com.osomapps.pt.goals.Goal;
import com.osomapps.pt.goals.GoalRepository;
import com.osomapps.pt.goals.InUserGoalRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserFacebook;
import com.osomapps.pt.token.InUserGoal;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserLoginRepository;
import com.osomapps.pt.token.InUserLogout;
import com.osomapps.pt.token.InUserLogoutRepository;
import com.osomapps.pt.token.InUserRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import com.osomapps.pt.tokenemail.InUserEmail;
import com.osomapps.pt.tokenemail.NameValidator;
import com.osomapps.pt.tokenemail.SendEmailService;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock private InUserRepository inUserRepository;
    @Mock private InUserLoginRepository inUserLoginRepository;
    @Mock private InUserLogoutRepository inUserLogoutRepository;
    @Mock private GoalRepository goalRepository;
    @Mock private InUserGoalRepository inUserGoalRepository;
    @Mock private DataurlValidator dataurlValidator;
    @Mock private NameValidator nameValidator;
    @Mock private SendEmailService sendEmailService;
    @Mock private AdminProgramAssignService adminProgramAssignService;

    @InjectMocks private UserService userService;

    @Test(expected = UnauthorizedException.class)
    public void findOne() {
        userService.findOne("1");
    }

    @Test
    public void findOne_token_found() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser()
                        .setD_sex("male")
                        .setAge(32F)
                        .setHeight(180F)
                        .setWeight(50F)
                        .setD_level("1")
                        .setInUserGoals(
                                Arrays.asList(new InUserGoal().setGoal_value("{\"key\":10}"))));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        UserResponseDTO userResponseDTO = userService.findOne("1");
        assertThat(userResponseDTO.age, equalTo(32L));
        assertThat(userResponseDTO.height, equalTo(180L));
        assertThat(userResponseDTO.weight, equalTo(50L));
        assertThat(userResponseDTO.level, equalTo(UserLevel.Unexperienced));
    }

    @Test
    public void findOne_token_found_with_facebook_user() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser()
                        .setD_sex("male")
                        .setAge(32F)
                        .setHeight(180F)
                        .setWeight(50F)
                        .setD_level("1")
                        .setInUserGoals(
                                Arrays.asList(new InUserGoal().setGoal_value("{\"key\":10}")))
                        .setInUserFacebooks(
                                Arrays.asList(new InUserFacebook().setUser_name("Name"))));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        UserResponseDTO userResponseDTO = userService.findOne("1");
        assertThat(userResponseDTO.age, equalTo(32L));
        assertThat(userResponseDTO.height, equalTo(180L));
        assertThat(userResponseDTO.weight, equalTo(50L));
        assertThat(userResponseDTO.level, equalTo(UserLevel.Unexperienced));
    }

    @Test
    public void findOne_token_found_with_email_user() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser()
                        .setD_sex("male")
                        .setAge(32F)
                        .setHeight(180F)
                        .setWeight(50F)
                        .setD_level("1")
                        .setInUserGoals(
                                Arrays.asList(new InUserGoal().setGoal_value("{\"key\":10}")))
                        .setInUserEmails(
                                Arrays.asList(
                                        new InUserEmail().setUser_name("Name").setLogin("user"))));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        UserResponseDTO userResponseDTO = userService.findOne("1");
        assertThat(userResponseDTO.age, equalTo(32L));
        assertThat(userResponseDTO.height, equalTo(180L));
        assertThat(userResponseDTO.weight, equalTo(50L));
        assertThat(userResponseDTO.level, equalTo(UserLevel.Unexperienced));
    }

    @Test(expected = UnauthorizedException.class)
    public void findOne_token_with_logout() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Arrays.asList(new InUserLogout()));
        userService.findOne("1");
    }

    @Test
    public void findOne_token_found_without_data() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(new InUser());
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        UserResponseDTO userResponseDTO = userService.findOne("1");
        assertThat(userResponseDTO.age, equalTo(null));
        assertThat(userResponseDTO.height, equalTo(null));
        assertThat(userResponseDTO.weight, equalTo(null));
        assertThat(userResponseDTO.level, equalTo(null));
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUser() {
        userService.updateUser("1", new UserRequestDTO());
    }

    @Test
    public void updateUser_token_found() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setId(1L).setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(adminProgramAssignService.assign(any(InUser.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        userService.updateUser("1", new UserRequestDTO());
        verify(inUserRepository).save(any(InUser.class));
    }

    @Test
    public void updateUser_token_found_from_facebook() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser()
                        .setId(1L)
                        .setD_sex("male")
                        .setAge(32F)
                        .setHeight(180F)
                        .setWeight(50F)
                        .setInUserFacebooks(
                                Arrays.asList(new InUserFacebook().setUser_name("User"))));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(adminProgramAssignService.assign(any(InUser.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        userService.updateUser("1", new UserRequestDTO().setName("New name"));
        verify(inUserRepository).save(any(InUser.class));
    }

    @Test
    public void updateUser_token_found_from_email() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser()
                        .setId(1L)
                        .setD_sex("male")
                        .setAge(32F)
                        .setHeight(180F)
                        .setWeight(50F)
                        .setInUserEmails(Arrays.asList(new InUserEmail().setLogin("user"))));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(adminProgramAssignService.assign(any(InUser.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        userService.updateUser("1", new UserRequestDTO().setName("New name"));
        verify(inUserRepository).save(any(InUser.class));
    }

    @Test
    public void updateUser_token_found_with_data() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser()
                        .setId(1L)
                        .setD_sex("male")
                        .setAge(32F)
                        .setHeight(180F)
                        .setWeight(50F)
                        .setInUserGoals(Collections.emptyList()));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        when(goalRepository.findById(eq(1L))).thenReturn(Optional.of(new Goal()));
        when(inUserGoalRepository.save(any(InUserGoal.class))).thenAnswer(i -> i.getArguments()[0]);
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        when(adminProgramAssignService.assign(any(InUser.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        userService.updateUser(
                "1",
                new UserRequestDTO()
                        .setGender("gender")
                        .setAge(10L)
                        .setHeight(160L)
                        .setWeight(60L)
                        .setName("Name")
                        .setLevel(UserLevel.Experienced)
                        .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L))));
        verify(inUserRepository).save(any(InUser.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUser_token_found_with_wrong_name() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser(
                "1",
                new UserRequestDTO()
                        .setGender("gender")
                        .setAge(10L)
                        .setHeight(160L)
                        .setWeight(60L)
                        .setName("N")
                        .setLevel(UserLevel.Experienced)
                        .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L))));
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUser_token_found_with_data_wrong_data_url() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser(
                "1",
                new UserRequestDTO()
                        .setGender("gender")
                        .setAge(10L)
                        .setHeight(160L)
                        .setWeight(60L)
                        .setName("Name")
                        .setAvatar_dataurl("data")
                        .setLevel(UserLevel.Experienced)
                        .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L))));
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUser_token_found_with_data_wrong_name() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser(
                "1",
                new UserRequestDTO()
                        .setGender("gender")
                        .setAge(10L)
                        .setHeight(160L)
                        .setWeight(60L)
                        .setName("N")
                        .setLevel(UserLevel.Experienced)
                        .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L))));
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUser_token_found_with_data_goal_not_found() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser(
                "1",
                new UserRequestDTO()
                        .setGender("gender")
                        .setAge(10L)
                        .setHeight(160L)
                        .setWeight(60L)
                        .setName("Name")
                        .setLevel(UserLevel.Experienced)
                        .setGoals(Arrays.asList(new UserGoalRequestDTO().setId(1L))));
    }

    @Test(expected = UnauthorizedException.class)
    public void updateUser_token_found_with_data_more_than_two_goals() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(
                new InUser().setD_sex("male").setAge(32F).setHeight(180F).setWeight(50F));
        when(inUserLoginRepository.findByToken("1")).thenReturn(Arrays.asList(inUserLogin));
        when(inUserLogoutRepository.findByToken("1")).thenReturn(Collections.emptyList());
        userService.updateUser(
                "1",
                new UserRequestDTO()
                        .setGender("gender")
                        .setAge(10L)
                        .setHeight(160L)
                        .setWeight(60L)
                        .setName("Name")
                        .setLevel(UserLevel.Experienced)
                        .setGoals(
                                Arrays.asList(
                                        new UserGoalRequestDTO().setId(1L),
                                        new UserGoalRequestDTO().setId(2L),
                                        new UserGoalRequestDTO().setId(3L))));
    }
}
