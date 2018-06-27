package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new TokenRequestDTO("", ""), notNullValue());
    }
}
