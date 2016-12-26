package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseTypeResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseTypeResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseTypeResponseDTO exerciseTypeResponseDTO = new ExerciseTypeResponseDTO()
            .setId(null)
            .setName(null);
        assertThat(exerciseTypeResponseDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseTypeResponseDTO exerciseTypeResponseDTO = new ExerciseTypeResponseDTO();
        exerciseTypeResponseDTO.getId();
        exerciseTypeResponseDTO.getName();
        assertThat(exerciseTypeResponseDTO, notNullValue());
    }

}
