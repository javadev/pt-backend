package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class FacebookResponseTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new FacebookResponse(null, null, null, null, null), notNullValue());
    }
}
