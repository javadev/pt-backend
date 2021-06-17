package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TokenRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new TokenRequestDTO("", ""), notNullValue());
    }
}
