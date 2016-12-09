package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ExerciseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseDTO(1L, null, null, null), notNullValue());
    }
}
