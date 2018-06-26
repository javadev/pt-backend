package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseInputRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseInputRequestDTO(null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseInputRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseInputRequestDTO exerciseInputRequestDTO = new ExerciseInputRequestDTO()
            .setId(null);
        assertThat(exerciseInputRequestDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseInputRequestDTO exerciseInputRequestDTO = new ExerciseInputRequestDTO();
        exerciseInputRequestDTO.getId();
        assertThat(exerciseInputRequestDTO, notNullValue());
    }

}
