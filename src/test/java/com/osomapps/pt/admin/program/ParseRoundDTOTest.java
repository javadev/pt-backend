package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParseRoundDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseRoundDTO(1L, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseRoundDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseRoundDTO parseRoundDTO = new ParseRoundDTO();
        parseRoundDTO.setId(null);
        parseRoundDTO.setName(null);
        parseRoundDTO.setParts(null);
        assertThat(parseRoundDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseRoundDTO parseRoundDTO = new ParseRoundDTO();
        parseRoundDTO.getId();
        parseRoundDTO.getName();
        parseRoundDTO.getParts();
        assertThat(parseRoundDTO, notNullValue());
    }
}
