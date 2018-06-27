package com.osomapps.pt.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class UserResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserResponseDTO(null, null, null, null, null, null,
                null, null, null, null, null, null), notNullValue());
    }
}
