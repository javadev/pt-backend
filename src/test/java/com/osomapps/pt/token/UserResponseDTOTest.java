package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class UserResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserResponseDTO(1L, "", "", "", "", "", 1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO()
            .setId(1L)
            .setName("")
            .setEmail("")
            .setAvatar("")
            .setAvatar_dataurl("")
            .setGender("")
            .setAge(1L)
            .setBirthday(null)
            .setHeight(null)
            .setWeight(null);
        assertThat(userResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.getId();
        userResponseDTO.getName();
        userResponseDTO.getEmail();
        userResponseDTO.getAvatar();
        userResponseDTO.getAvatar_dataurl();
        userResponseDTO.getGender();
        userResponseDTO.getAge();
        userResponseDTO.getBirthday();
        userResponseDTO.getHeight();
        userResponseDTO.getWeight();
        assertThat(userResponseDTO, notNullValue());
    }

}
