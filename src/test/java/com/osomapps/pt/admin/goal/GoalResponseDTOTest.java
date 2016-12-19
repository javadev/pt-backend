package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GoalResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalResponseDTO(1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        GoalResponseDTO goalResponseDTO = new GoalResponseDTO();
        goalResponseDTO.setId(null);
        goalResponseDTO.setTitleEn(null);
        goalResponseDTO.setTitleNo(null);
        goalResponseDTO.setTitle2En(null);
        goalResponseDTO.setTitle2No(null);
        goalResponseDTO.setParameters(null);
        assertThat(goalResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        GoalResponseDTO goalResponseDTO = new GoalResponseDTO();
        goalResponseDTO.getId();
        goalResponseDTO.getTitleEn();
        goalResponseDTO.getTitleNo();
        goalResponseDTO.getTitle2En();
        goalResponseDTO.getTitle2No();
        goalResponseDTO.getParameters();
        assertThat(goalResponseDTO, notNullValue());
    }
}
