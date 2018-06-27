package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
