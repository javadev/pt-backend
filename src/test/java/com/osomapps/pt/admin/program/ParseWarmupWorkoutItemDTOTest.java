package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseWarmupWorkoutItemDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseWarmupWorkoutItemDTO(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseWarmupWorkoutItemDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseWarmupWorkoutItemDTO parseWarmupWorkoutItemDTO = new ParseWarmupWorkoutItemDTO();
        parseWarmupWorkoutItemDTO.setId(null);
        parseWarmupWorkoutItemDTO.setName(null);
        parseWarmupWorkoutItemDTO.setSpeed(null);
        parseWarmupWorkoutItemDTO.setIncline(null);
        parseWarmupWorkoutItemDTO.setTime_in_min(null);
        assertThat(parseWarmupWorkoutItemDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseWarmupWorkoutItemDTO parseWarmupWorkoutItemDTO = new ParseWarmupWorkoutItemDTO();
        parseWarmupWorkoutItemDTO.getId();
        parseWarmupWorkoutItemDTO.getName();
        parseWarmupWorkoutItemDTO.getSpeed();
        parseWarmupWorkoutItemDTO.getIncline();
        parseWarmupWorkoutItemDTO.getTime_in_min();
        assertThat(parseWarmupWorkoutItemDTO, notNullValue());
    }
}
