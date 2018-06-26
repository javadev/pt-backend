package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

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
        inWorkoutItemSet.setExercise_weight_percent(null);
        inWorkoutItemSet.setExercise_weight_in_kg(null);
        inWorkoutItemSet.setGoal_weight_coef(null);
        inWorkoutItemSet.setExercise_repetitions_percent(null);
        inWorkoutItemSet.setGoal_repetitions(null);
        inWorkoutItemSet.setExercise_time_percent(null);
        inWorkoutItemSet.setGoal_time_in_sec(null);
        inWorkoutItemSet.setExercise_speed_percent(null);
        inWorkoutItemSet.setGoal_speed(null);
        inWorkoutItemSet.setExercise_basis(null);
        assertThat(inWorkoutItemSet, notNullValue());
    }

    @Test
    public void getters() {
        InWorkoutItemSet inWorkoutItemSet = new InWorkoutItemSet();
        inWorkoutItemSet.getId();
        inWorkoutItemSet.getCreated();
        inWorkoutItemSet.getInWorkoutItem();
        inWorkoutItemSet.getExercise_weight_percent();
        inWorkoutItemSet.getExercise_weight_in_kg();
        inWorkoutItemSet.getGoal_weight_coef();
        inWorkoutItemSet.getExercise_repetitions_percent();
        inWorkoutItemSet.getGoal_repetitions();
        inWorkoutItemSet.getExercise_time_percent();
        inWorkoutItemSet.getGoal_time_in_sec();
        inWorkoutItemSet.getExercise_speed_percent();
        inWorkoutItemSet.getGoal_speed();
        inWorkoutItemSet.getExercise_basis();
        assertThat(inWorkoutItemSet, notNullValue());
    }
}
