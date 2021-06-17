package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;

public class ParseWorkoutItemTest {
    @Test
    public void createAllArgs() {
        assertThat(
                new ParseWorkoutItem(1L, null, null, null, null, null, null, null, null),
                notNullValue());
    }

    @Test
    public void setters() {
        ParseWorkoutItem parseWorkoutItem = new ParseWorkoutItem();
        parseWorkoutItem.setCreated(LocalDateTime.MAX);
        parseWorkoutItem.setColumn_index(null);
        parseWorkoutItem.setRow_index(null);
        parseWorkoutItem.setIn_workout_item_id(null);
        parseWorkoutItem.setParseWorkout(null);
        assertThat(parseWorkoutItem, notNullValue());
    }

    @Test
    public void getters() {
        ParseWorkoutItem parseWorkoutItem = new ParseWorkoutItem();
        parseWorkoutItem.getCreated();
        parseWorkoutItem.getColumn_index();
        parseWorkoutItem.getRow_index();
        parseWorkoutItem.getIn_workout_item_id();
        parseWorkoutItem.getParseWorkout();
        assertThat(parseWorkoutItem, notNullValue());
    }
}
