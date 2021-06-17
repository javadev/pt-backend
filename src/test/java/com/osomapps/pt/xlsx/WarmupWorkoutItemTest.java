package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WarmupWorkoutItemTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new WarmupWorkoutItem(null, 0, null, null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new WarmupWorkoutItem(null, 0, null, null, null).toString(), notNullValue());
    }
}
