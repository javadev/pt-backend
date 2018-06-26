package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseBodypartRequestDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseBodypartRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseBodypartRequestDTO exerciseBodypartRequestDTO = new ExerciseBodypartRequestDTO()
            .setId(null);
        assertThat(exerciseBodypartRequestDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseBodypartRequestDTO exerciseBodypartRequestDTO = new ExerciseBodypartRequestDTO();
        exerciseBodypartRequestDTO.getId();
        assertThat(exerciseBodypartRequestDTO, notNullValue());
    }

}
