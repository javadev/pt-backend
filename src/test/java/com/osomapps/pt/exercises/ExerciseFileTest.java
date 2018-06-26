package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseFileTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseFile(1L, null, null, null, null, null,
                null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseFile exerciseFile = new ExerciseFile();
        exerciseFile.setId(null);
        exerciseFile.setCreated(null);
        exerciseFile.setExercises(null);
        assertThat(exerciseFile, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseFile exerciseFile = new ExerciseFile();
        exerciseFile.getId();
        exerciseFile.getCreated();
        exerciseFile.getExercises();
        assertThat(exerciseFile, notNullValue());
    }
}
