package com.osomapps.pt.reportworkout;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class WorkoutItemSetReportResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WorkoutItemSetReportResponseDTO(
                1L, 1, 1, true, 1, 1, 1, 1), notNullValue());
    }

    @Test
    public void setters() {
        WorkoutItemSetReportResponseDTO workoutItemSetResponseRequestDTO = new WorkoutItemSetReportResponseDTO()
            .setId(1L)
            .setRepetitions(1)
            .setWeight(1)
            .setBodyweight(true)
            .setTime_in_sec(1)
            .setSpeed(1)
            .setIncline(1)
            .setResistance(1);
        assertThat(workoutItemSetResponseRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        WorkoutItemSetReportResponseDTO workoutItemSetResponseRequestDTO = new WorkoutItemSetReportResponseDTO();
        workoutItemSetResponseRequestDTO.getId();
        workoutItemSetResponseRequestDTO.getRepetitions();
        workoutItemSetResponseRequestDTO.getWeight();
        workoutItemSetResponseRequestDTO.getBodyweight();
        workoutItemSetResponseRequestDTO.getTime_in_sec();
        workoutItemSetResponseRequestDTO.getSpeed();
        workoutItemSetResponseRequestDTO.getIncline();
        workoutItemSetResponseRequestDTO.getResistance();
        assertThat(workoutItemSetResponseRequestDTO, notNullValue());
    }

}
