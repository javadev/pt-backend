package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseInputTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseInput(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseInput exerciseInput = new ExerciseInput();
        exerciseInput.setCreated(null);
        exerciseInput.setExercises(null);
        assertThat(exerciseInput, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseInput exerciseInput = new ExerciseInput();
        exerciseInput.getCreated();
        exerciseInput.getExercises();
        assertThat(exerciseInput, notNullValue());
    }
}
