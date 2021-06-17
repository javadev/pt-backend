package com.osomapps.pt.auth;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class AuthUserResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new AuthUserResponseDTO(1L, null, null, null), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new AuthUserResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        AuthUserResponseDTO authUserResponseDTO = new AuthUserResponseDTO();
        authUserResponseDTO.setId(1L);
        authUserResponseDTO.setName(null);
        authUserResponseDTO.setLogin(null);
        authUserResponseDTO.setPermissions(null);
        assertThat(authUserResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        AuthUserResponseDTO authUserResponseDTO = new AuthUserResponseDTO();
        authUserResponseDTO.getId();
        authUserResponseDTO.getName();
        authUserResponseDTO.getLogin();
        authUserResponseDTO.getPermissions();
        assertThat(authUserResponseDTO, notNullValue());
    }
}
