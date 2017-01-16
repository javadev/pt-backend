package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserTypeRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserTypeRequestDTO(1L), notNullValue());
    }

    @Test
    public void setters() {
        UserTypeRequestDTO userTypeRequestDTO = new UserTypeRequestDTO();
        userTypeRequestDTO.setId(null);
        assertThat(userTypeRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserTypeRequestDTO userTypeRequestDTO = new UserTypeRequestDTO();
        userTypeRequestDTO.getId();
        assertThat(userTypeRequestDTO, notNullValue());
    }
}
