package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ParsePartDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParsePartDTO(1L, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParsePartDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParsePartDTO parsePartDTO = new ParsePartDTO();
        parsePartDTO.setId(null);
        parsePartDTO.setName(null);
        parsePartDTO.setWorkouts(null);
        assertThat(parsePartDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParsePartDTO parsePartDTO = new ParsePartDTO();
        parsePartDTO.getId();
        parsePartDTO.getName();
        parsePartDTO.getWorkouts();
        assertThat(parsePartDTO, notNullValue());
    }
}
