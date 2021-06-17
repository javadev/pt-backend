package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExerciseResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseResponseDTO exerciseResponseDTO =
                new ExerciseResponseDTO()
                        .setId(null)
                        .setExerciseId(null)
                        .setNameEn(null)
                        .setNameNo(null)
                        .setDescriptionEn(null)
                        .setDescriptionNo(null)
                        .setBodypart(null)
                        .setEquipmentType(null)
                        .setTypes(null)
                        .setInputs(null)
                        .setOutputs(null)
                        .setFiles(null)
                        .setCardioPercent(null);
        assertThat(exerciseResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseResponseDTO exerciseResponseDTO = new ExerciseResponseDTO();
        exerciseResponseDTO.getId();
        exerciseResponseDTO.getExerciseId();
        exerciseResponseDTO.getNameEn();
        exerciseResponseDTO.getNameNo();
        exerciseResponseDTO.getDescriptionEn();
        exerciseResponseDTO.getDescriptionNo();
        exerciseResponseDTO.getBodypart();
        exerciseResponseDTO.getEquipmentType();
        exerciseResponseDTO.getTypes();
        exerciseResponseDTO.getInputs();
        exerciseResponseDTO.getOutputs();
        exerciseResponseDTO.getFiles();
        exerciseResponseDTO.getCardioPercent();
        assertThat(exerciseResponseDTO, notNullValue());
    }
}
