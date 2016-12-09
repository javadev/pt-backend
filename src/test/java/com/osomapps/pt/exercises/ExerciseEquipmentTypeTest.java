package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseEquipmentTypeTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseEquipmentType(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ExerciseEquipmentType exerciseEquipmentType = new ExerciseEquipmentType();
        exerciseEquipmentType.setCreated(null);
        exerciseEquipmentType.setExercises(null);
        assertThat(exerciseEquipmentType, notNullValue());
    }

    @Test
    public void getters() {
        ExerciseEquipmentType exerciseEquipmentType = new ExerciseEquipmentType();
        exerciseEquipmentType.getCreated();
        exerciseEquipmentType.getExercises();
        assertThat(exerciseEquipmentType, notNullValue());
    }
}
