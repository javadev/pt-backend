package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WorkoutItemSetResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(
                new WorkoutItemSetResponseDTO(1, null, null, null, null, null, null),
                notNullValue());
    }

    @Test
    public void setters() {
        WorkoutItemSetResponseDTO workoutItemSetResponseDTO =
                new WorkoutItemSetResponseDTO()
                        .setRepetitions(null)
                        .setWeight(null)
                        .setBodyweight(null)
                        .setTime_in_sec(null)
                        .setSpeed(null)
                        .setIncline(null)
                        .setResistance(null);
        assertThat(workoutItemSetResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutItemSetResponseDTO workoutItemSetResponseDTO = new WorkoutItemSetResponseDTO();
        workoutItemSetResponseDTO.getRepetitions();
        workoutItemSetResponseDTO.getWeight();
        workoutItemSetResponseDTO.getBodyweight();
        workoutItemSetResponseDTO.getTime_in_sec();
        workoutItemSetResponseDTO.getSpeed();
        workoutItemSetResponseDTO.getIncline();
        workoutItemSetResponseDTO.getResistance();
        assertThat(workoutItemSetResponseDTO, notNullValue());
    }
}
