package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GoalTypeResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalTypeResponseDTO(1L, null), notNullValue());
    }

    @Test
    public void setters() {
        GoalTypeResponseDTO goalTypeResponseDTO = new GoalTypeResponseDTO();
        goalTypeResponseDTO.setId(null);
        goalTypeResponseDTO.setName(null);
        assertThat(goalTypeResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        GoalTypeResponseDTO goalTypeResponseDTO = new GoalTypeResponseDTO();
        goalTypeResponseDTO.getId();
        goalTypeResponseDTO.getName();
        assertThat(goalTypeResponseDTO, notNullValue());
    }
}
