package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseImageDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseImageDTO(1L, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseImageDTO exerciseImageDTO = new ExerciseImageDTO();
        exerciseImageDTO.setId(null);
        exerciseImageDTO.setFileName(null);
        assertThat(exerciseImageDTO, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseImageDTO exerciseImageDTO = new ExerciseImageDTO();
        exerciseImageDTO.getId();
        exerciseImageDTO.getFileName();
        assertThat(exerciseImageDTO, notNullValue());
    }

}
