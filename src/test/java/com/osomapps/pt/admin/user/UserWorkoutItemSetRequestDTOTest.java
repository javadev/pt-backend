package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserWorkoutItemSetRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWorkoutItemSetRequestDTO(1, 1, null), notNullValue());
    }

    @Test
    public void setters() {
        UserWorkoutItemSetRequestDTO userWorkoutItemSetRequestDTO = new UserWorkoutItemSetRequestDTO();
        userWorkoutItemSetRequestDTO.setRepetitions(null);
        userWorkoutItemSetRequestDTO.setWeight(null);
        userWorkoutItemSetRequestDTO.setBodyweight(null);
        assertThat(userWorkoutItemSetRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWorkoutItemSetRequestDTO userWorkoutItemSetRequestDTO = new UserWorkoutItemSetRequestDTO();
        userWorkoutItemSetRequestDTO.getRepetitions();
        userWorkoutItemSetRequestDTO.getWeight();
        userWorkoutItemSetRequestDTO.getBodyweight();
        assertThat(userWorkoutItemSetRequestDTO, notNullValue());
    }
}
