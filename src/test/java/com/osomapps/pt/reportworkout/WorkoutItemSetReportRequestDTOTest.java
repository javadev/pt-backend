package com.osomapps.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WorkoutItemSetReportRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemSetReportRequestDTO(1L, 1, 1, true, 1, 1, 1, 1), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutItemSetReportRequestDTO workoutItemSetReportRequestDTO =
                new WorkoutItemSetReportRequestDTO();
        workoutItemSetReportRequestDTO.setId(1L);
        assertThat(workoutItemSetReportRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutItemSetReportRequestDTO workoutItemSetReportRequestDTO =
                new WorkoutItemSetReportRequestDTO();
        workoutItemSetReportRequestDTO.getId();
        assertThat(workoutItemSetReportRequestDTO, notNullValue());
    }
}
