package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserRequestDTO(1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setId(null);
        userRequestDTO.setPassword(null);
        userRequestDTO.setLevel(null);
        assertThat(userRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.getId();
        userRequestDTO.getPassword();
        userRequestDTO.getLevel();
        assertThat(userRequestDTO, notNullValue());
    }

}
