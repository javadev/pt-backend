package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseOutputRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseOutputRequestDTO(null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseOutputRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseOutputRequestDTO exerciseOutputRequestDTO = new ExerciseOutputRequestDTO()
            .setId(null);
        assertThat(exerciseOutputRequestDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseOutputRequestDTO exerciseOutputRequestDTO = new ExerciseOutputRequestDTO();
        exerciseOutputRequestDTO.getId();
        assertThat(exerciseOutputRequestDTO, notNullValue());
    }

}
