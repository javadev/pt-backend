package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseBodypartResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseBodypartResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseBodypartResponseDTO exerciseBodypartResponseDTO = new ExerciseBodypartResponseDTO()
            .setId(null)
            .setNameEn(null)
            .setNameNo(null);
        assertThat(exerciseBodypartResponseDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseBodypartResponseDTO exerciseBodypartResponseDTO = new ExerciseBodypartResponseDTO();
        exerciseBodypartResponseDTO.getId();
        exerciseBodypartResponseDTO.getNameEn();
        exerciseBodypartResponseDTO.getNameNo();
        assertThat(exerciseBodypartResponseDTO, notNullValue());
    }

}
