package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseSheetsTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseSheets(null, null), notNullValue());
    }
}
