package com.osomapps.pt.reportphoto;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.goals.InUserPhotoRepository;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.token.InUserPhoto;
import com.osomapps.pt.user.UserService;
import java.io.ByteArrayOutputStream;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportPhotoFileServiceTest {

    @Mock
    private InUserPhotoRepository inUserPhotoRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private ReportPhotoFileService reportPhotoFileService;

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() throws Exception {
        when(userService.checkUserToken(anyString())).thenReturn(new InUserLogin());
        reportPhotoFileService.findOne("1", 1L, "", new ByteArrayOutputStream());
    }

    @Test
    public void findOne() throws Exception {
        when(userService.checkUserToken(anyString())).thenReturn(new InUserLogin());
        when(inUserPhotoRepository.findOne(eq(1L))).thenReturn(new InUserPhoto()
                .setData_url("data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7"));
        reportPhotoFileService.findOne("1", 1L, "", new ByteArrayOutputStream());
        verify(inUserPhotoRepository).findOne(eq(1L));
    }
}
