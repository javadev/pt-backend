package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class InUserTypeTest {
    @Test
    public void createAllArgs() {
        assertThat(new InUserType(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserType inUserType = new InUserType();
        inUserType.setCreated(null);
        inUserType.setInUsers(null);
        assertThat(inUserType, notNullValue());
    }

    @Test
    public void getters() {
        InUserType inUserType = new InUserType();
        inUserType.getCreated();
        inUserType.getInUsers();
        assertThat(inUserType, notNullValue());
    }
}
