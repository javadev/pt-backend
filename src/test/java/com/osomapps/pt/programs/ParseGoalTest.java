package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseGoalTest {
    @Test
    public void createAllArgs() {
        assertThat(new ParseGoal(
                1L, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseGoal parseGoal = new ParseGoal();
        parseGoal.setCreated(LocalDateTime.MAX);
        parseGoal.setParseProgram(null);
        assertThat(parseGoal, notNullValue());
    }

    @Test
    public void getters() {
        ParseGoal parseGoal = new ParseGoal();
        parseGoal.getCreated();
        parseGoal.getParseProgram();
        assertThat(parseGoal, notNullValue());
    }
}
