package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ProgramResponseDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new ProgramResponseDTO(null, null, null, null, null, null, null, null, null), notNullValue());
    }
    
    @Test
    public void setters() {
        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.setId(null);
        programResponseDTO.setName(null);
        programResponseDTO.setFileName(null);
        programResponseDTO.setFileSize(null);
        programResponseDTO.setFileType(null);
        programResponseDTO.setDataUrl(null);
        programResponseDTO.setUpdated(null);
        programResponseDTO.setParseExercises(null);
        programResponseDTO.setParseGoals(null);
        assertThat(programResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        ProgramResponseDTO programResponseDTO = new ProgramResponseDTO();
        programResponseDTO.getId();
        programResponseDTO.getName();
        programResponseDTO.getFileName();
        programResponseDTO.getFileSize();
        programResponseDTO.getFileType();
        programResponseDTO.getDataUrl();
        programResponseDTO.getUpdated();
        programResponseDTO.getParseExercises();
        programResponseDTO.getParseGoals();
        assertThat(programResponseDTO, notNullValue());
    }
}
