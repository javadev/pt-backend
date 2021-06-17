package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class OutputTest {
    @Test
    public void createAllArgs() {
        assertThat(new Output(null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        Output output =
                new Output()
                        .setRepetitions(null)
                        .setWeights(null)
                        .setTimeInMins(null)
                        .setSpeeds(null)
                        .setInclines(null)
                        .setResistances(null);
        assertThat(output, notNullValue());
    }

    @Test
    public void getters() {
        Output output = new Output();
        output.getRepetitions();
        output.getWeights();
        output.getSpeeds();
        output.getInclines();
        output.getResistances();
        assertThat(output, notNullValue());
    }

    @Test
    public void tostring() {
        Output output = new Output();
        assertThat(output.toString(), notNullValue());
    }
}
