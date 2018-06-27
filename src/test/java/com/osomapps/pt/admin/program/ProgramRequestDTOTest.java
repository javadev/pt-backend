package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ProgramRequestDTOTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new ProgramRequestDTO(null, null, null, null, null), notNullValue());
    }

    @Test
    public void builder() {
        ProgramRequestDTO programRequestDTO = ProgramRequestDTO.builder().build();
        assertThat(programRequestDTO, notNullValue());
    }
}
