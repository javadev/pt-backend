package com.osomapps.pt.auth;

import com.osomapps.pt.admin.ptuser.PtUser;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

@ExtendWith(MockitoExtension.class)
public class SecurityContextHelperTest {

    @BeforeEach
    public void init() {
        User user = new User("user", "notused", AuthorityUtils.createAuthorityList("ROLE_USER"));
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);        
    }

    @Test
    public void getPrincipal() {
        assertThat(new SecurityContextHelper().getPrincipal(), equalTo("user"));
    }

    @Test
    public void getCredentials() {
        assertThat(new SecurityContextHelper().getCredentials(), equalTo("notused"));
    }

    @Test
    public void getUserDetails_null() {
        assertThat(new SecurityContextHelper().getUserDetails(), nullValue());
    }

    @Test
    public void getUserDetails() {
        Authentication auth = mock(Authentication.class);
        when(auth.getPrincipal()).thenReturn(new CustomUserDetails(new PtUser()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        assertThat(new SecurityContextHelper().getUserDetails(), notNullValue());
    }

    @Test
    public void getAuthorities() {
        assertThat(new SecurityContextHelper().getAuthorities().toString(), equalTo("[ROLE_USER]"));
    }

}
