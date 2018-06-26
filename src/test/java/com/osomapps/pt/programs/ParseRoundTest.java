package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseRoundTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseRound(
                1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseRound parseRound = new ParseRound();
        parseRound.setCreated(LocalDateTime.MAX);
        parseRound.setParseUserGroup(null);
        assertThat(parseRound, notNullValue());
    }

    @Test
    public void getters() {
        ParseRound parseRound = new ParseRound();
        parseRound.getCreated();
        parseRound.getParseUserGroup();
        assertThat(parseRound, notNullValue());
    }
}
