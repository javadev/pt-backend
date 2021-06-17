package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserSignupRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new UserSignupRequestDTO(null, null, null), notNullValue());
    }
}
