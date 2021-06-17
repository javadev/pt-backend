package com.osomapps.pt.goals;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GoalDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalDTO(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new GoalDTO(), notNullValue());
    }

    @Test
    public void setters() {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.setId(1L);
        goalDTO.setTitle(null);
        goalDTO.setTitle2(null);
        goalDTO.setParameters(null);
        goalDTO.setType(null);
        assertThat(goalDTO, notNullValue());
    }

    @Test
    public void getters() {
        GoalDTO goalDTO = new GoalDTO();
        goalDTO.getId();
        goalDTO.getTitle();
        goalDTO.getTitle2();
        goalDTO.getParameters();
        goalDTO.getType();
        assertThat(goalDTO, notNullValue());
    }
}
