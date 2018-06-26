package com.osomapps.pt.goals;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class GoalTest {
    @Test
    public void createAllArgs() {
        assertThat(new Goal(1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        Goal goal = new Goal();
        goal.setCreated(null);
        assertThat(goal, notNullValue());
    }

    @Test
    public void getters() {
        Goal goal = new Goal();
        goal.getCreated();
        assertThat(goal, notNullValue());
    }
}
