package com.osomapps.pt.admin.goal;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class GoalRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new GoalRequestDTO(null, null, null, null, null), notNullValue());
    }
}
