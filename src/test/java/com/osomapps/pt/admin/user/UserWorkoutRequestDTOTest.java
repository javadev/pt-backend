package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserWorkoutRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWorkoutRequestDTO(null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserWorkoutRequestDTO userWorkoutRequestDTO = new UserWorkoutRequestDTO();
        userWorkoutRequestDTO.setWarmup(null);
        assertThat(userWorkoutRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWorkoutRequestDTO userWorkoutRequestDTO = new UserWorkoutRequestDTO();
        userWorkoutRequestDTO.getWarmup();
        assertThat(userWorkoutRequestDTO, notNullValue());
    }
}
