package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InUserLogoutTest {
    @Test
    public void createAllArgs() {
        assertThat(new InUserLogout(
                1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserLogout inUserLogout = new InUserLogout()
            .setId(1L)
            .setCreated(null)
            .setInUser(null)
            .setToken(null)
            .setIp_address(null);
        assertThat(inUserLogout, notNullValue());
    }

    @Test
    public void getters() {
        InUserLogout inUserLogout = new InUserLogout();
        inUserLogout.getId();
        inUserLogout.getCreated();
        inUserLogout.getInUser();
        inUserLogout.getToken();
        inUserLogout.getIp_address();
        assertThat(inUserLogout, notNullValue());
    }
}
