package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserLevelResponseDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new UserLevelResponseDTO(1, null), notNullValue());
    }

    @Test
    public void setters() {
        UserLevelResponseDTO userLevelResponseDTO = new UserLevelResponseDTO();
        userLevelResponseDTO.setId(null);
        userLevelResponseDTO.setName(null);
        assertThat(userLevelResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserLevelResponseDTO userLevelResponseDTO = new UserLevelResponseDTO();
        userLevelResponseDTO.getId();
        userLevelResponseDTO.getName();
        assertThat(userLevelResponseDTO, notNullValue());
    }
}
