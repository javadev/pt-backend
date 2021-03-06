package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;

public class ParseWorkoutItemSetTest {
    @Test
    public void allArgsConstructor() {
        assertThat(
                new ParseWorkoutItemSet(
                        1L, null, null, null, null, null, null, null, null, null, null),
                notNullValue());
    }

    @Test
    public void setters() {
        ParseWorkoutItemSet parseWorkoutItemSet = new ParseWorkoutItemSet();
        parseWorkoutItemSet.setId(null);
        parseWorkoutItemSet.setCreated(LocalDateTime.MAX);
        parseWorkoutItemSet.setParseWorkoutItem(null);
        assertThat(parseWorkoutItemSet, notNullValue());
    }

    @Test
    public void getters() {
        ParseWorkoutItemSet parseWorkoutItemSet = new ParseWorkoutItemSet();
        parseWorkoutItemSet.getId();
        parseWorkoutItemSet.getCreated();
        parseWorkoutItemSet.getParseWorkoutItem();
        assertThat(parseWorkoutItemSet, notNullValue());
    }
}
