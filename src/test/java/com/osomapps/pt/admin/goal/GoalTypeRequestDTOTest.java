package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class GoalTypeRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalTypeRequestDTO(1L), notNullValue());
    }
}
