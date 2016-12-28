package com.osomapps.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InWorkoutItemReportTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new InWorkoutItemReport(
                1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InWorkoutItemReport inWorkoutItemReport = new InWorkoutItemReport();
        inWorkoutItemReport.setCreated(LocalDateTime.MAX);
        inWorkoutItemReport.setInWorkoutItem(null);
        assertThat(inWorkoutItemReport, notNullValue());
    }

    @Test
    public void getters() {
        InWorkoutItemReport inWorkoutItemReport = new InWorkoutItemReport();
        inWorkoutItemReport.getCreated();
        inWorkoutItemReport.getInWorkoutItem();
        assertThat(inWorkoutItemReport, notNullValue());
    }
}
