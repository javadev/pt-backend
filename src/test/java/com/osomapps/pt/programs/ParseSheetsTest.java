package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseSheetsTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseSheets(null, null), notNullValue());
    }
}
