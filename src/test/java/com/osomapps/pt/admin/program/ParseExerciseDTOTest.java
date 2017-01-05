package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseExerciseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseExerciseDTO(1, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseExerciseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseExerciseDTO parseExerciseDTO = new ParseExerciseDTO();
        parseExerciseDTO.setExercise_id(null);
        parseExerciseDTO.setExercise_name(null);
        parseExerciseDTO.setUser_group_1_percent(null);
        parseExerciseDTO.setUser_group_2_percent(null);
        parseExerciseDTO.setUser_group_3_percent(null);
        parseExerciseDTO.setUser_group_4_percent(null);
        parseExerciseDTO.setBasis_for_calculations(null);
        assertThat(parseExerciseDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseExerciseDTO parseExerciseDTO = new ParseExerciseDTO();
        parseExerciseDTO.getExercise_id();
        parseExerciseDTO.getExercise_name();
        parseExerciseDTO.getUser_group_1_percent();
        parseExerciseDTO.getUser_group_2_percent();
        parseExerciseDTO.getUser_group_3_percent();
        parseExerciseDTO.getUser_group_4_percent();
        parseExerciseDTO.getBasis_for_calculations();
        assertThat(parseExerciseDTO, notNullValue());
    }

}
