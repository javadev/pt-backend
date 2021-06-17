package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenEmailSignupResponseDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new TokenEmailSignupResponseDTO(null, null), notNullValue());
    }

    @Test
    public void getters() {
        assertThat(new TokenEmailSignupResponseDTO().getToken(), nullValue());
        assertThat(new TokenEmailSignupResponseDTO().getUser(), nullValue());
    }

    @Test
    public void setter() {
        assertThat(new TokenEmailSignupResponseDTO().setToken(null), notNullValue());
        assertThat(new TokenEmailSignupResponseDTO().setUser(null), notNullValue());
    }
}
