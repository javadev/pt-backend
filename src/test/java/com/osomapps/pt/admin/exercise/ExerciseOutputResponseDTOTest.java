package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseOutputResponseDTOTest {
    @Test
    public void noArgsConstructor() {
        assertThat(new ExerciseOutputResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseOutputResponseDTO exerciseOutputResponseDTO = new ExerciseOutputResponseDTO()
            .setId(null)
            .setName(null);
        assertThat(exerciseOutputResponseDTO, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseOutputResponseDTO exerciseOutputResponseDTO = new ExerciseOutputResponseDTO();
        exerciseOutputResponseDTO.getId();
        exerciseOutputResponseDTO.getName();
        assertThat(exerciseOutputResponseDTO, notNullValue());
    }

}
