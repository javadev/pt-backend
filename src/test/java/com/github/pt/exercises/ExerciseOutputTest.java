package com.github.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseOutputTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseOutput(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseOutput exerciseOutput = new ExerciseOutput();
        exerciseOutput.setCreated(null);
        exerciseOutput.setExercises(null);
        assertThat(exerciseOutput, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseOutput exerciseOutput = new ExerciseOutput();
        exerciseOutput.getCreated();
        exerciseOutput.getExercises();
        assertThat(exerciseOutput, notNullValue());
    }
}
