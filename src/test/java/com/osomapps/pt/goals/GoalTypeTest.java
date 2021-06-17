package com.osomapps.pt.goals;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GoalTypeTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalType(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        GoalType goalType = new GoalType();
        goalType.setCreated(null);
        goalType.setGoals(null);
        assertThat(goalType, notNullValue());
    }

    @Test
    public void getters() {
        GoalType goalType = new GoalType();
        goalType.getCreated();
        goalType.getGoals();
        assertThat(goalType, notNullValue());
    }
}
