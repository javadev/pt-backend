package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class InputTest {
    @Test
    public void createAllArgs() {
        assertThat(new Input(null, null), notNullValue());
    }

    @Test
    public void setters() {
        Input input = new Input()
            .setExercise(null)
            .setSets(null);
        assertThat(input, notNullValue());
    }

    @Test
    public void getters() {
        Input input = new Input();
        input.getExercise();
        input.getSets();
        assertThat(input, notNullValue());
    }

    @Test
    public void tostring() {
        Input input = new Input();
        assertThat(input.toString(), notNullValue());
    }
}
