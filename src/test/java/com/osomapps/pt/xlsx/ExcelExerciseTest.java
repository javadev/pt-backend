package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExcelExerciseTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExcelExercise(null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(
                new ExcelExercise(null, null, null, null, null, null, null).toString(),
                notNullValue());
    }
}
