package com.github.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class InWorkoutTest {
    @Test
    public void createAllArgs() {
        assertThat(new InWorkout(
                1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InWorkout inWorkout = new InWorkout();
        inWorkout.setCreated(LocalDateTime.MAX);
        assertThat(inWorkout, notNullValue());
    }

    @Test
    public void getters() {
        InWorkout inWorkout = new InWorkout();
        inWorkout.getCreated();
        assertThat(inWorkout, notNullValue());
    }
}
