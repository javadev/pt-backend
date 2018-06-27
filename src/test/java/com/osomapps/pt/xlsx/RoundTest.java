package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class RoundTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new Round(null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new Round(null, null).toString(), notNullValue());
    }
}
