package com.github.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class WorkoutItemReportRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemReportRequestDTO(
                1L, 1, 1, 1, false), notNullValue());
    }
}
