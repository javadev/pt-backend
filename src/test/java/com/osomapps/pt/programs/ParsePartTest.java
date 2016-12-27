package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParsePartTest {
    @Test
    public void createAllArgs() {
        assertThat(new ParsePart(
                1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParsePart parsePart = new ParsePart();
        parsePart.setCreated(LocalDateTime.MAX);
        parsePart.setParseRound(null);
        assertThat(parsePart, notNullValue());
    }

    @Test
    public void getters() {
        ParsePart parsePart = new ParsePart();
        parsePart.getCreated();
        parsePart.getParseRound();
        assertThat(parsePart, notNullValue());
    }
}
