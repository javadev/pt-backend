package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class WorkoutItemResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new WorkoutItemResponseDTO(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutItemResponseDTO workoutItemResponseDTO = new WorkoutItemResponseDTO()
            .setId(null)
            .setExercise_id(null)
            .setExercise_name(null)
            .setExercise_type(null)
            .setSets(null);
        assertThat(workoutItemResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutItemResponseDTO workoutItemResponseDTO = new WorkoutItemResponseDTO();
        workoutItemResponseDTO.getId();
        workoutItemResponseDTO.getExercise_id();
        workoutItemResponseDTO.getExercise_name();
        workoutItemResponseDTO.getExercise_type();
        workoutItemResponseDTO.getSets();
        assertThat(workoutItemResponseDTO, notNullValue());
    }
}
