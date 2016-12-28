package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseWorkoutItemSetDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseWorkoutItemSetDTO(1, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseWorkoutItemSetDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseWorkoutItemSetDTO parseWorkoutItemSetDTO = new ParseWorkoutItemSetDTO();
        parseWorkoutItemSetDTO.setRepetitions(null);
        parseWorkoutItemSetDTO.setRepetitions_to_failure(null);        
        parseWorkoutItemSetDTO.setWeight(null);
        parseWorkoutItemSetDTO.setBodyweight(null);
        parseWorkoutItemSetDTO.setTime_in_min(null);
        parseWorkoutItemSetDTO.setSpeed(null);
        parseWorkoutItemSetDTO.setIncline(null);
        parseWorkoutItemSetDTO.setResistance(null);
        assertThat(parseWorkoutItemSetDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseWorkoutItemSetDTO parseWorkoutItemSetDTO = new ParseWorkoutItemSetDTO();
        parseWorkoutItemSetDTO.getRepetitions();
        parseWorkoutItemSetDTO.getRepetitions_to_failure();        
        parseWorkoutItemSetDTO.getWeight();
        parseWorkoutItemSetDTO.getBodyweight();
        parseWorkoutItemSetDTO.getTime_in_min();
        parseWorkoutItemSetDTO.getSpeed();
        parseWorkoutItemSetDTO.getIncline();
        parseWorkoutItemSetDTO.getResistance();
        assertThat(parseWorkoutItemSetDTO, notNullValue());
    }

    @Test
    public void builder() {
        ParseWorkoutItemSetDTO parseWorkoutItemSetDTO = ParseWorkoutItemSetDTO.builder().build();
        assertThat(parseWorkoutItemSetDTO, notNullValue());
    }

}
