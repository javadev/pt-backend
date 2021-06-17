package com.osomapps.pt.programs;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProgramResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new ProgramResponseDTO(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.setName(null);
        programResponseDTO.setType(null);
        assertThat(programResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.getName();
        programResponseDTO.getType();
        assertThat(programResponseDTO, notNullValue());
    }
}
