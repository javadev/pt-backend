package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseUserGroupDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseUserGroupDTO(1L, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseUserGroupDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseUserGroupDTO parseUserGroupDTO = new ParseUserGroupDTO();
        parseUserGroupDTO.setId(null);
        parseUserGroupDTO.setName(null);
        parseUserGroupDTO.setRounds(null);
        assertThat(parseUserGroupDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseUserGroupDTO parseUserGroupDTO = new ParseUserGroupDTO();
        parseUserGroupDTO.getId();
        parseUserGroupDTO.getName();
        parseUserGroupDTO.getRounds();
        assertThat(parseUserGroupDTO, notNullValue());
    }
}
