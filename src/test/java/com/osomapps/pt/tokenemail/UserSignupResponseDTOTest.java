package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserSignupResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserSignupResponseDTO(1L, null, null, null, null, null, null, null
                , null, null, null, null), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new UserSignupResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        UserSignupResponseDTO userSignupResponseDTO = new UserSignupResponseDTO()
            .setId(1L)
            .setName(null)
            .setEmail(null)
            .setAvatar(null)
            .setAvatar_dataurl(null)
            .setGender(null)
            .setAge(null)
            .setBirthday(null)
            .setLevel(null)
            .setGoals(null)
            .setHeight(null)
            .setWeight(null);
        assertThat(userSignupResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserSignupResponseDTO userSignupResponseDTO = new UserSignupResponseDTO();
        userSignupResponseDTO.getId();
        userSignupResponseDTO.getName();
        userSignupResponseDTO.getEmail();
        userSignupResponseDTO.getAvatar();
        userSignupResponseDTO.getAvatar_dataurl();
        userSignupResponseDTO.getGender();
        userSignupResponseDTO.getAge();
        userSignupResponseDTO.getBirthday();
        userSignupResponseDTO.getLevel();
        userSignupResponseDTO.getGoals();
        userSignupResponseDTO.getHeight();
        userSignupResponseDTO.getWeight();
        assertThat(userSignupResponseDTO, notNullValue());
    }
}
