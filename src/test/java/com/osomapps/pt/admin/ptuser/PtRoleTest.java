package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PtRoleTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtRole(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        PtRole ptRole = new PtRole();
        ptRole.setCreated(null);
        ptRole.setPtUsers(null);
        assertThat(ptRole, notNullValue());
    }

    @Test
    public void getters() {
        PtRole ptRole = new PtRole();
        ptRole.getCreated();
        ptRole.getPtUsers();
        assertThat(ptRole, notNullValue());
    }
}
