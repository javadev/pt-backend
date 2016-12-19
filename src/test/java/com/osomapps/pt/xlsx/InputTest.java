package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InputTest {
    @Test
    public void createAllArgs() {
        assertThat(new Input(null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        Input input = new Input()
            .setExercise(null)
            .setRepetitions(null)
            .setBodyweight(null)
            .setWeight(null)
            .setSpeed(null);
        assertThat(input, notNullValue());
    }

    @Test
    public void getters() {
        Input input = new Input();
        input.getExercise();
        input.getRepetitions();
        input.getBodyweight();
        input.getWeight();
        input.getSpeed();
        assertThat(input, notNullValue());
    }

    @Test
    public void tostring() {
        Input input = new Input();
        assertThat(input.toString(), notNullValue());
    }
}
