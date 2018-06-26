package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseTypeRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseTypeRequestDTO(null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseTypeRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseTypeRequestDTO exerciseTypeRequestDTO = new ExerciseTypeRequestDTO()
            .setId(null);
        assertThat(exerciseTypeRequestDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseTypeRequestDTO exerciseTypeRequestDTO = new ExerciseTypeRequestDTO();
        exerciseTypeRequestDTO.getId();
        assertThat(exerciseTypeRequestDTO, notNullValue());
    }

}
