package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PartTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new Part(null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new Part(null, null).toString(), notNullValue());
    }
}
