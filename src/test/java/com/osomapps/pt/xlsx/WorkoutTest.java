package com.osomapps.pt.xlsx;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class WorkoutTest {
    @Test
    public void createAllArgs() {
        assertThat(new Workout(null, 0, 0, null, null), notNullValue());
    }

    @Test
    public void setters() {
        Workout workout = new Workout()
            .setColumnIndex(0)
            .setRowIndex(0);
        assertThat(workout, notNullValue());
    }

    @Test
    public void getters() {
        Workout workout = new Workout();
        workout.getColumnIndex();
        workout.getRowIndex();
        assertThat(workout, notNullValue());
    }

    @Test
    public void tostring() {
        Workout workout = new Workout();
        assertThat(workout.toString(), notNullValue());
    }
}
