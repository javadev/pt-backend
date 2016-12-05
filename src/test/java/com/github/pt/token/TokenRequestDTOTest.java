package com.github.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class TokenRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new TokenRequestDTO("", ""), notNullValue());
    }
}
