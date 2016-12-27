package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseWorkoutItemDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseWorkoutItemDTO(1L, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseWorkoutItemDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseWorkoutItemDTO parseWorkoutItemDTO = new ParseWorkoutItemDTO();
        parseWorkoutItemDTO.setId(null);
        parseWorkoutItemDTO.setName(null);        
        parseWorkoutItemDTO.setSets(null);
        parseWorkoutItemDTO.setRepetitions(null);
        parseWorkoutItemDTO.setWeight(null);
        parseWorkoutItemDTO.setBodyweight(null);
        parseWorkoutItemDTO.setTime_in_min(null);
        parseWorkoutItemDTO.setSpeed(null);
        parseWorkoutItemDTO.setResistance(null);
        assertThat(parseWorkoutItemDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseWorkoutItemDTO parseWorkoutItemDTO = new ParseWorkoutItemDTO();
        parseWorkoutItemDTO.getId();
        parseWorkoutItemDTO.getName();        
        parseWorkoutItemDTO.getSets();
        parseWorkoutItemDTO.getRepetitions();
        parseWorkoutItemDTO.getWeight();
        parseWorkoutItemDTO.getBodyweight();
        parseWorkoutItemDTO.getTime_in_min();
        parseWorkoutItemDTO.getSpeed();
        parseWorkoutItemDTO.getResistance();
        assertThat(parseWorkoutItemDTO, notNullValue());
    }

}
