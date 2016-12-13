package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AuthUserServiceTest {
    
    @Mock
    private SecurityContextHelper securityContextHelper;
    @InjectMocks
    private AuthUserService authUserService;
    
    @Test
    public void findOne() {
        AuthUserResponseDTO authUserResponseDTO = authUserService.findOne();
        assertThat(authUserResponseDTO, notNullValue());
    }
    
    @Test
    public void findOne_found() {
        when(securityContextHelper.getUserDetails()).thenReturn(new CustomUserDetails(new PtUser()));
        AuthUserResponseDTO authUserResponseDTO = authUserService.findOne();
        assertThat(authUserResponseDTO, notNullValue());
    }
    
}
