package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseEquipmentTypeRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseEquipmentTypeRequestDTO(null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseEquipmentTypeRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseEquipmentTypeRequestDTO exerciseEquipmentTypeRequestDTO = new ExerciseEquipmentTypeRequestDTO()
            .setId(null);
        assertThat(exerciseEquipmentTypeRequestDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseEquipmentTypeRequestDTO exerciseEquipmentTypeRequestDTO = new ExerciseEquipmentTypeRequestDTO();
        exerciseEquipmentTypeRequestDTO.getId();
        assertThat(exerciseEquipmentTypeRequestDTO, notNullValue());
    }

}
