package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

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
