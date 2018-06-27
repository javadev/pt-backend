package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class UserGoalResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGoalResponseDTO(null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserGoalResponseDTO userGoalResponseDTO = new UserGoalResponseDTO();
        userGoalResponseDTO.setId(null);
        userGoalResponseDTO.setValues(null);
        assertThat(userGoalResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserGoalResponseDTO userGoalResponseDTO = new UserGoalResponseDTO();
        userGoalResponseDTO.getId();
        userGoalResponseDTO.getValues();
        assertThat(userGoalResponseDTO, notNullValue());
    }
}
