package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExerciseFileResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseFileResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseFileResponseDTO exerciseFileResponseDTO =
                new ExerciseFileResponseDTO()
                        .setId(null)
                        .setFile_name(null)
                        .setFile_size(null)
                        .setFile_type(null)
                        .setData_url(null);
        assertThat(exerciseFileResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseFileResponseDTO exerciseFileResponseDTO = new ExerciseFileResponseDTO();
        exerciseFileResponseDTO.getId();
        exerciseFileResponseDTO.getFile_name();
        exerciseFileResponseDTO.getFile_size();
        exerciseFileResponseDTO.getFile_type();
        exerciseFileResponseDTO.getData_url();
        assertThat(exerciseFileResponseDTO, notNullValue());
    }
}
