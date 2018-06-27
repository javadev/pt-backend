package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseFilePreviewTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseFilePreview(1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseFilePreview exerciseFilePreview = new ExerciseFilePreview();
        exerciseFilePreview.setCreated(null);
        exerciseFilePreview.setExercises(null);
        assertThat(exerciseFilePreview, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseFilePreview exerciseFilePreview = new ExerciseFilePreview();
        exerciseFilePreview.getCreated();
        exerciseFilePreview.getExercises();
        assertThat(exerciseFilePreview, notNullValue());
    }

}
