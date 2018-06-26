package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class GoalParameterResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalParameterResponseDTO(1L, null), notNullValue());
    }

    @Test
    public void setters() {
        GoalParameterResponseDTO goalParameterResponseDTO = new GoalParameterResponseDTO();
        goalParameterResponseDTO.setId(null);
        goalParameterResponseDTO.setName(null);
        assertThat(goalParameterResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        GoalParameterResponseDTO goalParameterResponseDTO = new GoalParameterResponseDTO();
        goalParameterResponseDTO.getId();
        goalParameterResponseDTO.getName();
        assertThat(goalParameterResponseDTO, notNullValue());
    }
}
