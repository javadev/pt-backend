package com.osomapps.pt.admin.exercise;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseRequestDTO(null, null, null, null, null, null,
                null, null, null, null, null, null), notNullValue());
    }
}
