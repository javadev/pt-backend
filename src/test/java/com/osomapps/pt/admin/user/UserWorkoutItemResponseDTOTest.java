package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserWorkoutItemResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserWorkoutItemResponseDTO(1L, null, null, null, null,
                null, null, null, null, null, null, null, null, null, null,
                null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserWorkoutItemResponseDTO userWorkoutItemResponseDTO = new UserWorkoutItemResponseDTO();
        userWorkoutItemResponseDTO.setId(null);
        userWorkoutItemResponseDTO.setExercise_id(null);
        userWorkoutItemResponseDTO.setExercise_name(null);
        userWorkoutItemResponseDTO.setSets(null);
        userWorkoutItemResponseDTO.setRepetitions(null);
        userWorkoutItemResponseDTO.setRepetitionsToFailure(null);
        userWorkoutItemResponseDTO.setWeight(null);
        userWorkoutItemResponseDTO.setBodyweight(null);
        userWorkoutItemResponseDTO.setTimeInSec(null);
        userWorkoutItemResponseDTO.setSpeed(null);
        userWorkoutItemResponseDTO.setIncline(null);
        userWorkoutItemResponseDTO.setResistance(null);
        userWorkoutItemResponseDTO.setReportSets(null);
        userWorkoutItemResponseDTO.setReportRepetitions(null);
        userWorkoutItemResponseDTO.setReportWeight(null);
        userWorkoutItemResponseDTO.setReportTimeInMin(null);
        userWorkoutItemResponseDTO.setReportSpeed(null);
        userWorkoutItemResponseDTO.setReportIncline(null);
        userWorkoutItemResponseDTO.setReportResistance(null);
        assertThat(userWorkoutItemResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserWorkoutItemResponseDTO userWorkoutItemResponseDTO = new UserWorkoutItemResponseDTO();
        userWorkoutItemResponseDTO.getId();
        userWorkoutItemResponseDTO.getExercise_id();
        userWorkoutItemResponseDTO.getExercise_name();
        userWorkoutItemResponseDTO.getSets();
        userWorkoutItemResponseDTO.getRepetitions();
        userWorkoutItemResponseDTO.getRepetitionsToFailure();
        userWorkoutItemResponseDTO.getWeight();
        userWorkoutItemResponseDTO.getBodyweight();
        userWorkoutItemResponseDTO.getTimeInSec();
        userWorkoutItemResponseDTO.getSpeed();
        userWorkoutItemResponseDTO.getIncline();
        userWorkoutItemResponseDTO.getResistance();
        userWorkoutItemResponseDTO.getReportSets();
        userWorkoutItemResponseDTO.getReportRepetitions();
        userWorkoutItemResponseDTO.getReportWeight();
        userWorkoutItemResponseDTO.getReportTimeInMin();
        userWorkoutItemResponseDTO.getReportSpeed();
        userWorkoutItemResponseDTO.getReportIncline();
        userWorkoutItemResponseDTO.getReportResistance();
        assertThat(userWorkoutItemResponseDTO, notNullValue());
    }
}
