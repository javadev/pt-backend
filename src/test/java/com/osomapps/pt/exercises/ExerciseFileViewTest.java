package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseFileViewTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseFileView(1L, null, null, null, null, null
                ), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseFileView exerciseFile = new ExerciseFileView();
        exerciseFile.setId(null);
        exerciseFile.setCreated(null);
        exerciseFile.setFile_size(null);
        exerciseFile.setFile_type(null);
        exerciseFile.setExercises(null);
        assertThat(exerciseFile, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseFileView exerciseFile = new ExerciseFileView();
        exerciseFile.getId();
        exerciseFile.getCreated();
        exerciseFile.getFile_size();
        exerciseFile.getFile_type();
        exerciseFile.getExercises();
        assertThat(exerciseFile, notNullValue());
    }

}
