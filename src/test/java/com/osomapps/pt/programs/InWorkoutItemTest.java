package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.mockito.internal.listeners.CollectCreatedMocks;

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
                null), notNullValue());
    }

    @Test
    public void setters() {
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        inWorkoutItem.setId(1L);
        inWorkoutItem.setCreated(LocalDateTime.MAX);
        inWorkoutItem.setInWorkoutItemReports(Collections.emptyList());
        inWorkoutItem.setInWorkoutItemSets(Collections.emptyList());
        assertThat(inWorkoutItem, notNullValue());
    }

    @Test
    public void getters() {
        InWorkoutItem inWorkoutItem = new InWorkoutItem();
        inWorkoutItem.getId();
        inWorkoutItem.getCreated();
        inWorkoutItem.getInWorkoutItemReports();
        inWorkoutItem.getInWorkoutItemSets();
        assertThat(inWorkoutItem, notNullValue());
    }

}
