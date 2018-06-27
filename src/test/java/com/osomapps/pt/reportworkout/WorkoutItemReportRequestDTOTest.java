package com.osomapps.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkoutItemReportRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemReportRequestDTO(1L, null), notNullValue());
    }
}
