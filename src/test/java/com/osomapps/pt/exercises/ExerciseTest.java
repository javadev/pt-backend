package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExerciseTest {
    @Test
    public void createAllArgs() {
        assertThat(
                new Exercise(1L, null, null, null, null, null, null, null, null, null, null, null),
                notNullValue());
    }

    @Test
    public void setters() {
        Exercise exercise = new Exercise();
        exercise.setCreated(null);
        assertThat(exercise, notNullValue());
    }

    @Test
    public void getters() {
        Exercise exercise = new Exercise();
        exercise.getCreated();
        assertThat(exercise, notNullValue());
    }
}
