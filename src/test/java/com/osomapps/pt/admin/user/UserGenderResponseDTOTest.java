package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserGenderResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGenderResponseDTO(null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new UserGenderResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        UserGenderResponseDTO userGenderResponseDTO = new UserGenderResponseDTO();
        userGenderResponseDTO.setId(null);
        userGenderResponseDTO.setName(null);
        assertThat(userGenderResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserGenderResponseDTO userGenderResponseDTO = new UserGenderResponseDTO();
        userGenderResponseDTO.getId();
        userGenderResponseDTO.getName();
        assertThat(userGenderResponseDTO, notNullValue());
    }
}
