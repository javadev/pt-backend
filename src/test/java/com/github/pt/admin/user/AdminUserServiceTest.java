package com.github.pt.admin.user;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.programs.InProgram;
import com.github.pt.programs.InWarmupWorkoutItem;
import com.github.pt.programs.InWorkout;
import com.github.pt.programs.InWorkoutItem;
import com.github.pt.programs.InWorkoutItemReport;
import com.github.pt.programs.InWorkoutItemSetReport;
import com.github.pt.token.InUser;
import com.github.pt.token.InUserFacebook;
import com.github.pt.token.InUserFacebookRepository;
import com.github.pt.token.InUserRepository;
import com.github.pt.tokenemail.EmailValidator;
import com.github.pt.tokenemail.InUserEmail;
import com.github.pt.tokenemail.InUserEmailRepository;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
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

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() {
        adminUserService.findOne(1L);
    }

    private InProgram getInProgram() {
        return new InProgram()
        .setInWorkouts(Arrays.asList(
                new InWorkout()
                    .setInWarmupWorkoutItems(Arrays.asList(new InWarmupWorkoutItem()
                    ))
                    .setInWorkoutItems(Arrays.asList(
                            new InWorkoutItem()
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
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        UserResponseDTO userResponseDTO = adminUserService.create(
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );
        assertThat(userResponseDTO.getName(), equalTo(null));
    }

    @Test(expected = UnauthorizedException.class)
    public void create_emailValidator() {
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("email", "Invalid empty email");
            return null;
        }).when(emailValidator).validate(anyObject(), any(Errors.class));
        adminUserService.create(
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminUserService.update(1L,new UserRequestDTO().setType(new UserTypeRequestDTO()));
    }

    @Test
    public void update() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
            .setInUserEmails(Collections.emptyList())
            .setInUserFacebooks(Arrays.asList(new InUserFacebook())));
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        UserResponseDTO userResponseDTO = adminUserService.update(1L,
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );
        assertThat(userResponseDTO.getName(), equalTo(null));
    }

    @Test
    public void update_with_eamil() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(
                new InUser()
            .setInUserEmails(Arrays.asList(new InUserEmail()))
            .setInUserFacebooks(Arrays.asList(new InUserFacebook())));
        when(inUserRepository.save(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        UserResponseDTO userResponseDTO = adminUserService.update(1L,
                new UserRequestDTO()
                .setType(new UserTypeRequestDTO())
        );
        assertThat(userResponseDTO.getName(), equalTo(null));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminUserService.delete(1L);
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
