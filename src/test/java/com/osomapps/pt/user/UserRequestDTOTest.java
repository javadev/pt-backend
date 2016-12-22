package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserRequestDTO(null, null, null, null, null, null, null), notNullValue());
    }
}
