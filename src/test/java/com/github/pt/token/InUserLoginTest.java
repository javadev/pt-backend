package com.github.pt.token;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.Test;

public class InUserLoginTest {
    @Test
    public void create() {
        assertThat(new InUser(), notNullValue());
    }

    @Test
    public void createAllArgs() {
        assertThat(new InUserLogin(
                1L, null, LocalDateTime.now(), "token", "ip_address"), notNullValue());
    }

    @Test
    public void setters() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setId(1L);
        inUserLogin.setInUser(null);
        inUserLogin.setCreated(LocalDateTime.MAX);
        inUserLogin.setToken(null);
        inUserLogin.setIp_address(null);
        assertThat(inUserLogin, notNullValue());
    }

    @Test
    public void getters() {
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.getId();
        inUserLogin.getCreated();
        inUserLogin.getInUser();
        inUserLogin.getToken();
        inUserLogin.getIp_address();
        assertThat(inUserLogin, notNullValue());
    }
}
