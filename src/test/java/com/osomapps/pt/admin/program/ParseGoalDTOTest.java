package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ParseGoalDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ParseGoalDTO(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new ParseGoalDTO(), notNullValue());
    }

    @Test
    public void setters() {
        ParseGoalDTO parseGoalDTO = new ParseGoalDTO();
        parseGoalDTO.setId(null);
        parseGoalDTO.setName(null);
        parseGoalDTO.setLoops(null);
        parseGoalDTO.setUserGroups(null);
        parseGoalDTO.setErrors(null);
        assertThat(parseGoalDTO, notNullValue());
    }

    @Test
    public void getters() {
        ParseGoalDTO parseGoalDTO = new ParseGoalDTO();
        parseGoalDTO.getId();
        parseGoalDTO.getName();
        parseGoalDTO.getLoops();
        parseGoalDTO.getUserGroups();
        parseGoalDTO.getErrors();
        assertThat(parseGoalDTO, notNullValue());
    }
}
