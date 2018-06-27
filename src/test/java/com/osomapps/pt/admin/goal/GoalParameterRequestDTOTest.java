package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class GoalParameterRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalParameterRequestDTO(1L), notNullValue());
    }

    @Test
    public void setters() {
        GoalParameterRequestDTO goalParameterRequestDTO = new GoalParameterRequestDTO();
        goalParameterRequestDTO.setId(null);
        assertThat(goalParameterRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        GoalParameterRequestDTO goalParameterRequestDTO = new GoalParameterRequestDTO();
        goalParameterRequestDTO.getId();
        assertThat(goalParameterRequestDTO, notNullValue());
    }

}
