package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExcelExerciseTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExcelExercise(null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new ExcelExercise(null, null, null, null, null, null, null).toString(), notNullValue());
    }
}
