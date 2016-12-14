package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import com.osomapps.pt.admin.ptuser.PtUserRepository;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private PtUserRepository ptUserRepository;
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    
    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_not_found() {
        customUserDetailsService.loadUserByUsername("username");
    }

    @Test
    public void loadUserByUsername() {
        PtUser ptUser = new PtUser();
        when(ptUserRepository.findByLogin(anyString())).thenReturn(Arrays.asList(ptUser));
        UserDetails userDetails = customUserDetailsService.loadUserByUsername("username");
        assertThat(userDetails instanceof CustomUserDetails, equalTo(true));
        assertThat(((CustomUserDetails) userDetails).getPtUser(), equalTo(ptUser));
    }

}
