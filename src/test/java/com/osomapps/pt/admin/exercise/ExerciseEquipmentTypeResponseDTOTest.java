package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseEquipmentTypeResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseEquipmentTypeResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseEquipmentTypeResponseDTO exerciseEquipmentTypeResponseDTO = new ExerciseEquipmentTypeResponseDTO()
            .setId(null)
            .setNameEn(null)
            .setNameNo(null);
        assertThat(exerciseEquipmentTypeResponseDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseEquipmentTypeResponseDTO exerciseEquipmentTypeResponseDTO = new ExerciseEquipmentTypeResponseDTO();
        exerciseEquipmentTypeResponseDTO.getId();
        exerciseEquipmentTypeResponseDTO.getNameEn();
        exerciseEquipmentTypeResponseDTO.getNameNo();
        assertThat(exerciseEquipmentTypeResponseDTO, notNullValue());
    }

}
