package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class UserWarmupWorkoutItemRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWarmupWorkoutItemRequestDTO(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new UserWarmupWorkoutItemRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        UserWarmupWorkoutItemRequestDTO userWarmupWorkoutItemRequestDTO =
                new UserWarmupWorkoutItemRequestDTO();
        userWarmupWorkoutItemRequestDTO.setExercise_id(null);
        userWarmupWorkoutItemRequestDTO.setExercise_name(null);
        userWarmupWorkoutItemRequestDTO.setSpeed(null);
        userWarmupWorkoutItemRequestDTO.setIncline(null);
        userWarmupWorkoutItemRequestDTO.setTime_in_sec(null);
        assertThat(userWarmupWorkoutItemRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWarmupWorkoutItemRequestDTO userWarmupWorkoutItemRequestDTO =
                new UserWarmupWorkoutItemRequestDTO();
        userWarmupWorkoutItemRequestDTO.getExercise_id();
        userWarmupWorkoutItemRequestDTO.getExercise_name();
        userWarmupWorkoutItemRequestDTO.getSpeed();
        userWarmupWorkoutItemRequestDTO.getIncline();
        userWarmupWorkoutItemRequestDTO.getTime_in_sec();
        assertThat(userWarmupWorkoutItemRequestDTO, notNullValue());
    }
}
