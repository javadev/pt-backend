package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserGoalRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGoalRequestDTO(null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new UserGoalRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        UserGoalRequestDTO userGoalRequestDTO = new UserGoalRequestDTO();
        userGoalRequestDTO.setId(null);
        userGoalRequestDTO.setValues(null);
        assertThat(userGoalRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserGoalRequestDTO userGoalRequestDTO = new UserGoalRequestDTO();
        userGoalRequestDTO.getId();
        userGoalRequestDTO.getValues();
        assertThat(userGoalRequestDTO, notNullValue());
    }
}
