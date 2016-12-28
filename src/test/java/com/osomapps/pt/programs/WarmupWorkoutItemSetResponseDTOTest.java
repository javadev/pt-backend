package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class WarmupWorkoutItemSetResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new WarmupWorkoutItemSetResponseDTO(1, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        WarmupWorkoutItemSetResponseDTO warmupWorkoutItemSetResponseDTO = new WarmupWorkoutItemSetResponseDTO()
            .setRepetitions(null)
            .setWeight(null)
            .setBodyweight(null)
            .setTime_in_min(null)
            .setSpeed(null)
            .setIncline(null)
            .setResistance(null);
        assertThat(warmupWorkoutItemSetResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WarmupWorkoutItemSetResponseDTO warmupWorkoutItemSetResponseDTO = new WarmupWorkoutItemSetResponseDTO();
        warmupWorkoutItemSetResponseDTO.getRepetitions();
        warmupWorkoutItemSetResponseDTO.getWeight();
        warmupWorkoutItemSetResponseDTO.getBodyweight();
        warmupWorkoutItemSetResponseDTO.getTime_in_min();
        warmupWorkoutItemSetResponseDTO.getSpeed();
        warmupWorkoutItemSetResponseDTO.getIncline();
        warmupWorkoutItemSetResponseDTO.getResistance();
        assertThat(warmupWorkoutItemSetResponseDTO, notNullValue());
    }
}
