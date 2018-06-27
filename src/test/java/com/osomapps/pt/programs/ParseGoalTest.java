package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseGoalTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseGoal(
                1L, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseGoal parseGoal = new ParseGoal();
        parseGoal.setCreated(LocalDateTime.MAX);
        parseGoal.setSheet_index(null);
        parseGoal.setParseProgram(null);
        assertThat(parseGoal, notNullValue());
    }

    @Test
    public void getters() {
        ParseGoal parseGoal = new ParseGoal();
        parseGoal.getCreated();
        parseGoal.getSheet_index();
        parseGoal.getParseProgram();
        assertThat(parseGoal, notNullValue());
    }
}
