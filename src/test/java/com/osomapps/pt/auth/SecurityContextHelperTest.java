package com.osomapps.pt.auth;

import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class SecurityContextHelperTest {

    @Before
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
    public void getAuthorities() {
        assertThat(new SecurityContextHelper().getAuthorities().toString(), equalTo("[ROLE_USER]"));
    }

}
