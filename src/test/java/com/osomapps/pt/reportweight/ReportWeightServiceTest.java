package com.osomapps.pt.reportweight;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReportWeightServiceTest {

    @Mock private InUserWeightRepository inUserWeightRepository;
    @Mock private UserService userService;

    @InjectMocks private ReportWeightService reportWeightService;

    @Test
    public void findAll() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserForLogin.setInUserWeights(Arrays.asList(new InUserWeight().setId(1L).setWeight(1F)));
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        reportWeightService.findAll("1");
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create() {
        InUserLogin inUserLogin = new InUserLogin();
        InUser inUserForLogin = new InUser();
        inUserForLogin.setId(10L);
        inUserLogin.setInUser(inUserForLogin);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(inUserWeightRepository.save(any(InUserWeight.class)))
                .thenReturn(new InUserWeight().setId(1L).setWeight(1F));
        reportWeightService.create("1", new WeightRequestDTO().setWeight(1L));
        verify(userService).checkUserToken(eq("1"));
    }

    @Test
    public void create_with_empty_token() {
        reportWeightService.create("", new WeightRequestDTO().setWeight(1L));
        verify(userService, never()).checkUserToken(anyString());
    }
}
