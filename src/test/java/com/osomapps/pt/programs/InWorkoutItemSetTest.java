package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InWorkoutItemSetTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new InWorkoutItemSet(
                1L, null, null, null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InWorkoutItemSet inWorkoutItemSet = new InWorkoutItemSet();
        inWorkoutItemSet.setId(null);
        inWorkoutItemSet.setCreated(LocalDateTime.MAX);
        inWorkoutItemSet.setInWorkoutItem(null);
        assertThat(inWorkoutItemSet, notNullValue());
    }

    @Test
    public void getters() {
        InWorkoutItemSet inWorkoutItemSet = new InWorkoutItemSet();
        inWorkoutItemSet.getId();
        inWorkoutItemSet.getCreated();
        inWorkoutItemSet.getInWorkoutItem();
        assertThat(inWorkoutItemSet, notNullValue());
    }
}
