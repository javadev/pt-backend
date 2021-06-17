package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenEmailResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new TokenEmailResponseDTO(null, null), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new TokenEmailResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        TokenEmailResponseDTO tokenEmailResponseDTO = new TokenEmailResponseDTO();
        tokenEmailResponseDTO.setToken(null);
        tokenEmailResponseDTO.setUser(null);
        assertThat(tokenEmailResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        TokenEmailResponseDTO tokenEmailResponseDTO = new TokenEmailResponseDTO();
        tokenEmailResponseDTO.getToken();
        tokenEmailResponseDTO.getUser();
        assertThat(tokenEmailResponseDTO, notNullValue());
    }
}
