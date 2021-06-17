package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InputSetTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new InputSet(null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void tostring() {
        InputSet inputSet = new InputSet();
        assertThat(inputSet.toString(), notNullValue());
    }
}
