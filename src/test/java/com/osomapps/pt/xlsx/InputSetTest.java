package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

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
