package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

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
