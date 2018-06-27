package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExcelGoalTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExcelGoal(null, 0, null, null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new ExcelGoal(null, 0, null, null, null).toString(), notNullValue());
    }
}
