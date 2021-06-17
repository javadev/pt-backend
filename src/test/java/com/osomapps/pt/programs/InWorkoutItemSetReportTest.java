package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;

public class InWorkoutItemSetReportTest {
    @Test
    public void createAllArgs() {
        assertThat(
                new InWorkoutItemSetReport(
                        1L, null, null, null, null, null, null, null, null, null),
                notNullValue());
    }

    @Test
    public void setters() {
        InWorkoutItemSetReport inWorkoutItemSetReport = new InWorkoutItemSetReport();
        inWorkoutItemSetReport.setCreated(LocalDateTime.MAX);
        inWorkoutItemSetReport.setInWorkoutItemReport(null);
        assertThat(inWorkoutItemSetReport, notNullValue());
    }

    @Test
    public void getters() {
        InWorkoutItemSetReport inWorkoutItemSetReport = new InWorkoutItemSetReport();
        inWorkoutItemSetReport.getCreated();
        inWorkoutItemSetReport.getInWorkoutItemReport();
        assertThat(inWorkoutItemSetReport, notNullValue());
    }
}
