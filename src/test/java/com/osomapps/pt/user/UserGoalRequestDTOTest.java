package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserGoalRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGoalRequestDTO(null, null), notNullValue());
    }
}
