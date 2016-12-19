package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class WarmupWorkoutItemResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WarmupWorkoutItemResponseDTO(1L, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new WarmupWorkoutItemResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        WarmupWorkoutItemResponseDTO warmupWorkoutItemResponseDTO = new WarmupWorkoutItemResponseDTO()
            .setId(1L)
            .setExercise_id(null)
            .setExercise_name(null)
            .setExercise_type(null)
            .setSpeed(null)
            .setIncline(null)
            .setTime_in_min(null);
        assertThat(warmupWorkoutItemResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WarmupWorkoutItemResponseDTO warmupWorkoutItemResponseDTO = new WarmupWorkoutItemResponseDTO();
        warmupWorkoutItemResponseDTO.getId();
        warmupWorkoutItemResponseDTO.getExercise_id();
        warmupWorkoutItemResponseDTO.getExercise_name();
        warmupWorkoutItemResponseDTO.getExercise_type();
        warmupWorkoutItemResponseDTO.getSpeed();
        warmupWorkoutItemResponseDTO.getIncline();
        warmupWorkoutItemResponseDTO.getTime_in_min();
        assertThat(warmupWorkoutItemResponseDTO, notNullValue());
    }

}
