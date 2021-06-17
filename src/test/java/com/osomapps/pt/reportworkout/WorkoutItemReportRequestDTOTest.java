package com.osomapps.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WorkoutItemReportRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemReportRequestDTO(1L, null), notNullValue());
    }
}
