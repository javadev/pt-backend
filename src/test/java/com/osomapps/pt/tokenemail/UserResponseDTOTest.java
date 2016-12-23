package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserResponseDTO(1L, null, null, null, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new UserResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO()
            .setId(1L)
            .setName(null)
            .setEmail(null)
            .setAvatar(null)
            .setAvatar_dataurl(null)
            .setGender(null)
            .setAge(null)
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
