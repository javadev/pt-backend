package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class InWorkoutItemTest {
    @Test
    public void create() {
        assertThat(new InWorkoutItem(), notNullValue());
    }

    @Test
    public void createAllArgs() {
        assertThat(new InWorkoutItem(
                1L, LocalDateTime.now(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null), notNullValue());
    }

    @Test
    public void setters() {
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        inWorkoutItem.setId(1L);
        inWorkoutItem.setCreated(LocalDateTime.MAX);
        inWorkoutItem.setIncline(1);
        inWorkoutItem.setResistance(1);
        assertThat(inWorkoutItem, notNullValue());
    }

    @Test
    public void getters() {
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        inWorkoutItem.getId();
        inWorkoutItem.getCreated();
        inWorkoutItem.getIncline();
        inWorkoutItem.getResistance();
        assertThat(inWorkoutItem, notNullValue());
    }

}
