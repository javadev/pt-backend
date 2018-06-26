package com.osomapps.pt.reportworkout;

import java.util.Collections;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkoutReportRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutReportRequestDTO(
                1L, Collections.emptyList()), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        workoutReportRequestDTO.setId(1L);
        workoutReportRequestDTO.setItems(null);
        assertThat(workoutReportRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutReportRequestDTO workoutReportRequestDTO = new WorkoutReportRequestDTO();
        workoutReportRequestDTO.getId();
        workoutReportRequestDTO.getItems();
        assertThat(workoutReportRequestDTO, notNullValue());
    }
}
