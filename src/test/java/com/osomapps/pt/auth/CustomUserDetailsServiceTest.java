package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import com.osomapps.pt.admin.ptuser.PtUserRepository;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock
    private PtUserRepository ptUserRepository;
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    
    @Test
    public void loadUserByUsername_not_found() {
        assertThrows(UsernameNotFoundException.class, () -> {customUserDetailsService.loadUserByUsername("username");});
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
