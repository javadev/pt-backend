package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseWorkoutTest {
    @Test
    public void createAllArgs() {
        assertThat(new ParseWorkout(
                1L, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseWorkout parseWorkout = new ParseWorkout();
        parseWorkout.setCreated(LocalDateTime.MAX);
        parseWorkout.setColumn_index(null);
        parseWorkout.setRow_index(null);
        parseWorkout.setParsePart(null);
        assertThat(parseWorkout, notNullValue());
    }

    @Test
    public void getters() {
        ParseWorkout parseWorkout = new ParseWorkout();
        parseWorkout.getCreated();
        parseWorkout.getColumn_index();
        parseWorkout.getRow_index();
        parseWorkout.getParsePart();
        assertThat(parseWorkout, notNullValue());
    }
}
