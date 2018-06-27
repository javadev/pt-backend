package com.osomapps.pt.reportworkout;

import java.util.Collections;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkoutReportResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutReportResponseDTO(
                1L, Collections.emptyList()), notNullValue());
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
