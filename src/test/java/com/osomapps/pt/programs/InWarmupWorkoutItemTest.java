package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;

public class InWarmupWorkoutItemTest {
    @Test
    public void createAllArgs() {
        assertThat(
                new InWarmupWorkoutItem(1L, null, null, null, null, null, null, null),
                notNullValue());
    }

    @Test
    public void setters() {
        InWarmupWorkoutItem inWarmupWorkoutItem = new InWarmupWorkoutItem();
        inWarmupWorkoutItem.setCreated(LocalDateTime.MAX);
        inWarmupWorkoutItem.setInWorkout(null);
        assertThat(inWarmupWorkoutItem, notNullValue());
    }

    @Test
    public void getters() {
        InWarmupWorkoutItem inWarmupWorkoutItem = new InWarmupWorkoutItem();
        inWarmupWorkoutItem.getCreated();
        inWarmupWorkoutItem.getInWorkout();
        assertThat(inWarmupWorkoutItem, notNullValue());
    }
}
