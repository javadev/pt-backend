package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseViewTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseView(1L, null, null, null, null, null, null, null
                ), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseView exerciseView = new ExerciseView();
        exerciseView.setId(null);
        exerciseView.setCreated(null);
        assertThat(exerciseView, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseView exerciseView = new ExerciseView();
        exerciseView.getId();
        exerciseView.getCreated();
        assertThat(exerciseView, notNullValue());
    }

}
