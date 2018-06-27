package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseBodypartTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseBodypart(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseBodypart exerciseBodypart = new ExerciseBodypart();
        exerciseBodypart.setCreated(null);
        exerciseBodypart.setExercises(null);
        assertThat(exerciseBodypart, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseBodypart exerciseBodypart = new ExerciseBodypart();
        exerciseBodypart.getCreated();
        exerciseBodypart.getExercises();
        assertThat(exerciseBodypart, notNullValue());
    }
}
