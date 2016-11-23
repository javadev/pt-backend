package com.github.pt;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class ResourceNotFoundExceptionTest {

    @Test
    public void create() {
        final ResourceNotFoundException exception = new ResourceNotFoundException();
        assertThat(exception.getMessage(), nullValue());
    }

    @Test
    public void createWithMessage() {
        final ResourceNotFoundException exception = new ResourceNotFoundException("message");
        assertThat(exception.getMessage(), equalTo("message"));
    }
}
