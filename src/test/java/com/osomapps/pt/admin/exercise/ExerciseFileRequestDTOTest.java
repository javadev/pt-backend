package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseFileRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseFileRequestDTO(null, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseFileRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseFileRequestDTO exerciseFileRequestDTO = new ExerciseFileRequestDTO()
            .setId(null);
        assertThat(exerciseFileRequestDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseFileRequestDTO exerciseFileRequestDTO = new ExerciseFileRequestDTO();
        exerciseFileRequestDTO.getId();
        assertThat(exerciseFileRequestDTO, notNullValue());
    }

}
