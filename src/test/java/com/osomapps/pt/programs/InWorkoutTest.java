package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class InWorkoutTest {
    @Test
    public void createAllArgs() {
        assertThat(new InWorkout(
                1L, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InWorkout inWorkout = new InWorkout();
        inWorkout.setCreated(LocalDateTime.MAX);
        inWorkout.setWorkout_index(null);
        assertThat(inWorkout, notNullValue());
    }

    @Test
    public void getters() {
        InWorkout inWorkout = new InWorkout();
        inWorkout.getCreated();
        inWorkout.getWorkout_index();
        assertThat(inWorkout, notNullValue());
    }
}
