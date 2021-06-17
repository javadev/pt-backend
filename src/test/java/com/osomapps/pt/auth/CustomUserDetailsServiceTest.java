package com.osomapps.pt.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import com.osomapps.pt.admin.ptuser.PtUser;
import com.osomapps.pt.admin.ptuser.PtUserRepository;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class CustomUserDetailsServiceTest {

    @Mock private PtUserRepository ptUserRepository;
    @InjectMocks private CustomUserDetailsService customUserDetailsService;

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
