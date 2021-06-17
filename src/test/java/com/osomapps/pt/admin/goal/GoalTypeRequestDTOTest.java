package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GoalTypeRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalTypeRequestDTO(1L), notNullValue());
    }
}
