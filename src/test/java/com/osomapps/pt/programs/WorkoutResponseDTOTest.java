package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WorkoutResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new WorkoutResponseDTO(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutResponseDTO workoutResponseDTO =
                new WorkoutResponseDTO().setId(null).setName(null).setWarmup(null).setItems(null);
        assertThat(workoutResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutResponseDTO workoutResponseDTO = new WorkoutResponseDTO();
        workoutResponseDTO.getId();
        workoutResponseDTO.getName();
        workoutResponseDTO.getWarmup();
        workoutResponseDTO.getItems();
        assertThat(workoutResponseDTO, notNullValue());
    }
}
