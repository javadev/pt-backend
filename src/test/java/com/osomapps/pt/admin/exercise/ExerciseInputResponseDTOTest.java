package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseInputResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseInputResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseInputResponseDTO exerciseInputResponseDTO = new ExerciseInputResponseDTO()
            .setId(null)
            .setName(null);
        assertThat(exerciseInputResponseDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseInputResponseDTO exerciseInputResponseDTO = new ExerciseInputResponseDTO();
        exerciseInputResponseDTO.getId();
        exerciseInputResponseDTO.getName();
        assertThat(exerciseInputResponseDTO, notNullValue());
    }

}
