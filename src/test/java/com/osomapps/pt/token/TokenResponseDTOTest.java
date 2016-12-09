package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class TokenResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new TokenResponseDTO("", null), notNullValue());
    }

    @Test
    public void setters() {
        TokenResponseDTO tokenResponseDTO = new TokenResponseDTO()
            .setToken("")
            .setUser(null);
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
