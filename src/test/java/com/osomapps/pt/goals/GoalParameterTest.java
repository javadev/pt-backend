package com.osomapps.pt.goals;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class GoalParameterTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalParameter(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        GoalParameter goalParameter = new GoalParameter();
        goalParameter.setId(null);
        goalParameter.setCreated(null);
        goalParameter.setGoals(null);
        assertThat(goalParameter, notNullValue());
    }

    @Test
    public void getters() {
        GoalParameter goalParameter = new GoalParameter();
        goalParameter.getId();
        goalParameter.getCreated();
        goalParameter.getGoals();
        assertThat(goalParameter, notNullValue());
    }
}
