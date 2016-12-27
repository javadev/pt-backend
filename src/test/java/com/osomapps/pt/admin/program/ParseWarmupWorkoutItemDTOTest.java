package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseWarmupWorkoutItemDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseGoalDTO(1L, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseGoalDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseGoalDTO parseGoalDTO = new ParseGoalDTO();
        parseGoalDTO.setId(null);
        parseGoalDTO.setName(null);
        parseGoalDTO.setUserGroups(null);
        parseGoalDTO.setErrors(null);
        assertThat(parseGoalDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseGoalDTO parseGoalDTO = new ParseGoalDTO();
        parseGoalDTO.getId();
        parseGoalDTO.getName();
        parseGoalDTO.getUserGroups();
        parseGoalDTO.getErrors();
        assertThat(parseGoalDTO, notNullValue());
    }
}
