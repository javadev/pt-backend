package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InUserFacebookTest {
    @Test
    public void createAllArgs() {
        assertThat(new InUserFacebook(
                1L, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserFacebook inUserFacebook = new InUserFacebook()
            .setId(1L)
            .setCreated(null)
            .setToken(null)
            .setDeviceId(null)
            .setUserId(null)
            .setBirthday(null);
        assertThat(inUserFacebook, notNullValue());
    }

    @Test
    public void getters() {
        InUserFacebook inUserFacebook = new InUserFacebook();
        inUserFacebook.getId();
        inUserFacebook.getCreated();
        inUserFacebook.getToken();
        inUserFacebook.getDeviceId();
        inUserFacebook.getUserId();
        inUserFacebook.getBirthday();
        assertThat(inUserFacebook, notNullValue());
    }
}
