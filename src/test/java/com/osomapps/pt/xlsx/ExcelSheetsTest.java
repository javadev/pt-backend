package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExcelSheetsTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExcelSheets(null, null), notNullValue());
    }

    @Test
    public void tostring() {
        assertThat(new ExcelSheets(null, null).toString(), notNullValue());
    }
}
