package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserWarmupWorkoutItemResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWarmupWorkoutItemResponseDTO(1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserWarmupWorkoutItemResponseDTO userWarmupWorkoutItemResponseDTO = new UserWarmupWorkoutItemResponseDTO();
        userWarmupWorkoutItemResponseDTO.setId(null);
        userWarmupWorkoutItemResponseDTO.setExercise_id(null);
        userWarmupWorkoutItemResponseDTO.setExercise_name(null);
        userWarmupWorkoutItemResponseDTO.setSpeed(null);
        userWarmupWorkoutItemResponseDTO.setIncline(null);
        userWarmupWorkoutItemResponseDTO.setTime_in_sec(null);
        assertThat(userWarmupWorkoutItemResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWarmupWorkoutItemResponseDTO userWarmupWorkoutItemResponseDTO = new UserWarmupWorkoutItemResponseDTO();
        userWarmupWorkoutItemResponseDTO.getId();
        userWarmupWorkoutItemResponseDTO.getExercise_id();
        userWarmupWorkoutItemResponseDTO.getExercise_name();
        userWarmupWorkoutItemResponseDTO.getSpeed();
        userWarmupWorkoutItemResponseDTO.getIncline();
        userWarmupWorkoutItemResponseDTO.getTime_in_sec();
        assertThat(userWarmupWorkoutItemResponseDTO, notNullValue());
    }
}
