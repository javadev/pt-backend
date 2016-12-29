package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new UserResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(null);
        userResponseDTO.setEmail(null);
        userResponseDTO.setType(null);
        userResponseDTO.setPrograms(null);
        assertThat(userResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.getId();
        userResponseDTO.getEmail();
        userResponseDTO.getType();
        userResponseDTO.getPrograms();
        assertThat(userResponseDTO, notNullValue());
    }
}
