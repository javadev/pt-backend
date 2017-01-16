package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserWorkoutItemRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWorkoutItemRequestDTO(1L, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserWorkoutItemRequestDTO userWorkoutItemRequestDTO = new UserWorkoutItemRequestDTO();
        userWorkoutItemRequestDTO.setExercise_id(null);
        userWorkoutItemRequestDTO.setExercise_name(null);
        userWorkoutItemRequestDTO.setSets(null);
        assertThat(userWorkoutItemRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWorkoutItemRequestDTO userWorkoutItemRequestDTO = new UserWorkoutItemRequestDTO();
        userWorkoutItemRequestDTO.getExercise_id();
        userWorkoutItemRequestDTO.getExercise_name();
        userWorkoutItemRequestDTO.getSets();
        assertThat(userWorkoutItemRequestDTO, notNullValue());
    }
}
