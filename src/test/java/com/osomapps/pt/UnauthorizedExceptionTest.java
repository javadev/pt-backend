package com.osomapps.pt;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

public class UnauthorizedExceptionTest {

    @Test
    public void create() {
        final UnauthorizedException exception = new UnauthorizedException();
        assertThat(exception.getMessage(), nullValue());
    }

    @Test
    public void createWithMessage() {
        final UnauthorizedException exception = new UnauthorizedException("message");
        assertThat(exception.getMessage(), equalTo("message"));
    }
}
