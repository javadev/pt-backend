package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

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
