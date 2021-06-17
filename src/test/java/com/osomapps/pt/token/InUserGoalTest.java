package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InUserGoalTest {
    @Test
    public void createAllArgs() {
        assertThat(new InUserGoal(1L, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserGoal inUserGoal =
                new InUserGoal()
                        .setId(1L)
                        .setCreated(null)
                        .setGoalId(null)
                        .setD_goal_title(null)
                        .setD_goal_title_2(null)
                        .setGoal_value(null)
                        .setInUsers(null)
                        .setInUserGoalType(null);
        assertThat(inUserGoal, notNullValue());
    }

    @Test
    public void getters() {
        InUserGoal inUserGoal = new InUserGoal();
        inUserGoal.getId();
        inUserGoal.getCreated();
        inUserGoal.getGoalId();
        inUserGoal.getD_goal_title();
        inUserGoal.getD_goal_title_2();
        inUserGoal.getGoal_value();
        inUserGoal.getInUsers();
        inUserGoal.getInUserGoalType();
        assertThat(inUserGoal, notNullValue());
    }
}
