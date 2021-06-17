package com.osomapps.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.Collections;
import org.junit.Test;

public class WorkoutReportResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutReportResponseDTO(1L, Collections.emptyList()), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutReportResponseDTO workoutReportResponseDTO = new WorkoutReportResponseDTO();
        workoutReportResponseDTO.setId(1L);
        workoutReportResponseDTO.setItems(null);
        assertThat(workoutReportResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutReportResponseDTO workoutReportResponseDTO = new WorkoutReportResponseDTO();
        workoutReportResponseDTO.getId();
        workoutReportResponseDTO.getItems();
        assertThat(workoutReportResponseDTO, notNullValue());
    }
}
