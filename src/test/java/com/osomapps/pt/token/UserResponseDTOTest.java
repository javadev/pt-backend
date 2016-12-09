package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class UserResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserResponseDTO(1L, "", "", "", 1L, null), notNullValue());
    }

    @Test
    public void setters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO()
            .setId(1L)
            .setName("")
            .setAvatar("")
            .setGender("")
            .setAge(1L)
            .setBirthday(null);
        assertThat(userResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.getId();
        userResponseDTO.getName();
        userResponseDTO.getAvatar();
        userResponseDTO.getGender();
        userResponseDTO.getAge();
        userResponseDTO.getBirthday();
        assertThat(userResponseDTO, notNullValue());
    }

}
