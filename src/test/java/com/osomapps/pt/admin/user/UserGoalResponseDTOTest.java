package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserGoalResponseDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new UserGoalResponseDTO(1L, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserGoalResponseDTO userGoalResponseDTO = new UserGoalResponseDTO();
        userGoalResponseDTO.setId(null);
        userGoalResponseDTO.setTitle(null);
        userGoalResponseDTO.setTitle2(null);
        assertThat(userGoalResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserGoalResponseDTO userGoalResponseDTO = new UserGoalResponseDTO();
        userGoalResponseDTO.getId();
        userGoalResponseDTO.getTitle();
        userGoalResponseDTO.getTitle2();
        assertThat(userGoalResponseDTO, notNullValue());
    }
}
