package com.osomapps.pt.exercises;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new ExerciseDTO(1L, null, null, null, null, null), notNullValue());
    }
}
