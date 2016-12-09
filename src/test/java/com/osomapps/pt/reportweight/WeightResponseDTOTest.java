package com.osomapps.pt.reportweight;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class WeightResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WeightResponseDTO(1L, 1L), notNullValue());
    }

    @Test
    public void createNoArgs() {
        assertThat(new WeightResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        WeightResponseDTO weightResponseDTO = new WeightResponseDTO();
        weightResponseDTO.setId(1L);
        weightResponseDTO.setWeight(null);
        assertThat(weightResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        WeightResponseDTO weightResponseDTO = new WeightResponseDTO();
        weightResponseDTO.getId();
        weightResponseDTO.getWeight();
        assertThat(weightResponseDTO, notNullValue());
    }

}
