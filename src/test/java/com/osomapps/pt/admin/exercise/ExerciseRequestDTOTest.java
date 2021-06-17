package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExerciseRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(
                new ExerciseRequestDTO(
                        null, null, null, null, null, null, null, null, null, null, null, null),
                notNullValue());
    }
}
