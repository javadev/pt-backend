package com.osomapps.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WorkoutItemReportResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemReportResponseDTO(1L, null), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutItemReportResponseDTO workoutItemReportResponseDTO =
                new WorkoutItemReportResponseDTO();
        workoutItemReportResponseDTO.setId(1L);
        workoutItemReportResponseDTO.setSets(null);
        assertThat(workoutItemReportResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutItemReportResponseDTO workoutItemReportResponseDTO =
                new WorkoutItemReportResponseDTO();
        workoutItemReportResponseDTO.getId();
        workoutItemReportResponseDTO.getSets();
        assertThat(workoutItemReportResponseDTO, notNullValue());
    }
}
