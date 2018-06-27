package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseWarmupWorkoutItemTest {
    @Test
    public void createAllArgs() {
        assertThat(new ParseWarmupWorkoutItem(
                1L, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ParseWarmupWorkoutItem parseWarmupWorkoutItem = new ParseWarmupWorkoutItem();
        parseWarmupWorkoutItem.setCreated(LocalDateTime.MAX);
        parseWarmupWorkoutItem.setParseWorkout(null);
        assertThat(parseWarmupWorkoutItem, notNullValue());
    }

    @Test
    public void getters() {
        ParseWarmupWorkoutItem parseWarmupWorkoutItem = new ParseWarmupWorkoutItem();
        parseWarmupWorkoutItem.getCreated();
        parseWarmupWorkoutItem.getParseWorkout();
        assertThat(parseWarmupWorkoutItem, notNullValue());
    }
}
