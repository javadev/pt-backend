package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new TokenResponseDTO("", null), notNullValue());
    }

    @Test
    public void setters() {
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO().setToken("").setUser(null);
        assertThat(tokenResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO();
        tokenResponseDTO.getToken();
        tokenResponseDTO.getUser();
        assertThat(tokenResponseDTO, notNullValue());
    }
}
