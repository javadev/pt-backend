package com.github.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class WorkoutItemReportResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemReportResponseDTO(
                1L, 1, 1, 1, false), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutItemReportResponseDTO workoutItemReportResponseDTO = new WorkoutItemReportResponseDTO();
        workoutItemReportResponseDTO.setId(1L);
        workoutItemReportResponseDTO.setSets(null);
        workoutItemReportResponseDTO.setRepetitions(null);
        workoutItemReportResponseDTO.setWeight(null);
        assertThat(workoutItemReportResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutItemReportResponseDTO workoutItemReportResponseDTO = new WorkoutItemReportResponseDTO();
        workoutItemReportResponseDTO.getId();
        workoutItemReportResponseDTO.getSets();
        workoutItemReportResponseDTO.getRepetitions();
        workoutItemReportResponseDTO.getWeight();
        assertThat(workoutItemReportResponseDTO, notNullValue());
    }
}
