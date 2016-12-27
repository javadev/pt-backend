package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseWorkoutDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseWorkoutDTO(1L, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseWorkoutDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseWorkoutDTO parseWorkoutDTO = new ParseWorkoutDTO();
        parseWorkoutDTO.setId(null);
        parseWorkoutDTO.setName(null);
        parseWorkoutDTO.setWarmupWorkoutItem(null);
        parseWorkoutDTO.setWorkoutItems(null);
        assertThat(parseWorkoutDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseWorkoutDTO parseWorkoutDTO = new ParseWorkoutDTO();
        parseWorkoutDTO.getId();
        parseWorkoutDTO.getName();
        parseWorkoutDTO.getWarmupWorkoutItem();
        parseWorkoutDTO.getWorkoutItems();
        assertThat(parseWorkoutDTO, notNullValue());
    }
}
