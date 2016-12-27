package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseProgramTest {
    @Test
    public void createAllArgs() {
        assertThat(new ParseProgram(
                1L, null, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseProgram parseProgram = new ParseProgram();
        parseProgram.setCreated(LocalDateTime.MAX);
        parseProgram.setActive(null);
        assertThat(parseProgram, notNullValue());
    }

    @Test
    public void getters() {
        ParseProgram parseProgram = new ParseProgram();
        parseProgram.getCreated();
        parseProgram.getActive();
        assertThat(parseProgram, notNullValue());
    }
}
