package com.osomapps.pt.auth;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.osomapps.pt.admin.ptuser.PtUser;
import org.junit.Test;

public class CustomUserDetailsTest {

    @Test
    public void getters() {
        CustomUserDetails customUserDetails =
                new CustomUserDetails(new PtUser().setIs_deleted(Boolean.FALSE));
        customUserDetails.getAuthorities();
        customUserDetails.getPassword();
        customUserDetails.getUsername();
        customUserDetails.isAccountNonExpired();
        customUserDetails.isAccountNonLocked();
        customUserDetails.isCredentialsNonExpired();
        customUserDetails.isEnabled();
        assertThat(customUserDetails, notNullValue());
        new CustomUserDetails(new PtUser().setIs_deleted(Boolean.TRUE)).isEnabled();
    }
}
