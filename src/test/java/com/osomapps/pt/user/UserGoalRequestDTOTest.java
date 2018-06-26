package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class UserGoalRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserGoalRequestDTO(null, null), notNullValue());
    }
}
