package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseExerciseTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseExercise(
                1L, null, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseExercise parseExercise = new ParseExercise();
        parseExercise.setId(null);
        parseExercise.setCreated(LocalDateTime.MAX);
        parseExercise.setParseProgram(null);
        assertThat(parseExercise, notNullValue());
    }

    @Test
    public void getters() {
        ParseExercise parseExercise = new ParseExercise();
        parseExercise.getId();
        parseExercise.getCreated();
        parseExercise.getParseProgram();
        assertThat(parseExercise, notNullValue());
    }
}
