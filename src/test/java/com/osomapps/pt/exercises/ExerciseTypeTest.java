package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseTypeTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseType(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.setCreated(null);
        exerciseType.setExercises(null);
        assertThat(exerciseType, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseType exerciseType = new ExerciseType();
        exerciseType.getCreated();
        exerciseType.getExercises();
        assertThat(exerciseType, notNullValue());
    }
}
