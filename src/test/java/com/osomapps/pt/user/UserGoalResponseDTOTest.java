package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserGoalResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGoalResponseDTO(null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserGoalResponseDTO userGoalResponseDTO = new UserGoalResponseDTO();
        userGoalResponseDTO.setId(null);
        userGoalResponseDTO.setValue(null);
        assertThat(userGoalResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserGoalResponseDTO userGoalResponseDTO = new UserGoalResponseDTO();
        userGoalResponseDTO.getId();
        userGoalResponseDTO.getValue();
        assertThat(userGoalResponseDTO, notNullValue());
    }
}
