package com.osomapps.pt.token;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class FacebookResponseTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new FacebookResponse(null, null, null, null, null), notNullValue());
    }
}
