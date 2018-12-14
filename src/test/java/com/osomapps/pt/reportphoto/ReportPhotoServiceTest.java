package com.osomapps.pt.reportphoto;

import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.reportweight.InUserWeight;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;
import com.osomapps.pt.goals.InUserPhotoRepository;
import com.osomapps.pt.token.InUserPhoto;
import com.osomapps.pt.tokenemail.DataurlValidator;
import java.util.List;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class ReportPhotoServiceTest {

    @Mock
    private InUserPhotoRepository inUserPhotoRepository;
    @Mock
    private UserService userService;
    @Mock
    private DataurlValidator dataurlValidator;

    @InjectMocks
    private ReportPhotoService reportPhotoService;

    @Test
    public void findAll() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser().setInUserPhotos(Arrays.asList(new InUserPhoto()));
        inUserForLogin.setId(10L);
        inUserForLogin.setInUserWeights(Arrays.asList(new InUserWeight().setId(1L).setWeight(1F)));
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        reportPhotoService.findAll("1");
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void findAll_empty_token() {
        List<PhotoResponseDTO> photoResponseDTOs = reportPhotoService.findAll("");
        assertThat(photoResponseDTOs, notNullValue());
    }

    @Test
    public void create() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser().setInUserGoals(Arrays.asList(new InUserGoal().setGoalId(1L)));
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(inUserPhotoRepository.save(any(InUserPhoto.class))).thenReturn(new InUserPhoto().setId(1L));
        reportPhotoService.create("1", new PhotoRequestDTO().setGoal_id(1L).setDataurl(
                "data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
        verify(userService).checkUserToken(eq("1"));
    }

    @Test(expected = UnauthorizedException.class)
    public void create_invalid_dataurl() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser().setInUserGoals(Arrays.asList(new InUserGoal().setGoalId(1L)));
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        doAnswer((Answer<Void>) (InvocationOnMock invocation) -> {
            Object[] args = invocation.getArguments();
            ((Errors) args[1]).reject("dataurlValidator", "dataurlValidator");
            return null;
        }).when(dataurlValidator).validate(anyObject(), any(Errors.class));
        reportPhotoService.create("1", new PhotoRequestDTO().setGoal_id(1L).setDataurl(
                "data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
    }

    @Test
    public void create_photo_png() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser().setInUserGoals(Arrays.asList(new InUserGoal().setGoalId(1L)));
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(inUserPhotoRepository.save(any(InUserPhoto.class))).thenReturn(new InUserPhoto().setId(1L));
        reportPhotoService.create("1", new PhotoRequestDTO().setGoal_id(1L).setDataurl(
                "data:image/png;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create_photo_jpg() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser().setInUserGoals(Arrays.asList(new InUserGoal().setGoalId(1L)));
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(inUserPhotoRepository.save(any(InUserPhoto.class))).thenReturn(new InUserPhoto().setId(1L));
        reportPhotoService.create("1", new PhotoRequestDTO().setGoal_id(1L).setDataurl(
                "data:image/jpeg;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create_with_empty_token() {
        reportPhotoService.create("", new PhotoRequestDTO().setGoal_id(1L).setDataurl(
                "data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
        verify(userService, never()).checkUserToken(anyString());
    }
}
