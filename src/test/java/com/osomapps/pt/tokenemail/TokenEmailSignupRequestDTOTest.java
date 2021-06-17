package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenEmailSignupRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new TokenEmailSignupRequestDTO(null, null, null), notNullValue());
    }
}
