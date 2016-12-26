package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class WorkoutItemTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new WorkoutItem(0, 0, null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new WorkoutItem(0, 0, null, null).toString(), notNullValue());
    }
}
