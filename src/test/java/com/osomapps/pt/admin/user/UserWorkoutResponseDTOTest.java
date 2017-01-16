package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserWorkoutResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWorkoutResponseDTO(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserWorkoutResponseDTO userWorkoutResponseDTO = new UserWorkoutResponseDTO();
        userWorkoutResponseDTO.setId(null);
        userWorkoutResponseDTO.setName(null);
        userWorkoutResponseDTO.setWarmup(null);
        assertThat(userWorkoutResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWorkoutResponseDTO userWorkoutResponseDTO = new UserWorkoutResponseDTO();
        userWorkoutResponseDTO.getId();
        userWorkoutResponseDTO.getName();
        userWorkoutResponseDTO.getWarmup();
        assertThat(userWorkoutResponseDTO, notNullValue());
    }
}
