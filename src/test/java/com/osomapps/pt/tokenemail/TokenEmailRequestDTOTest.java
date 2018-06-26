package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class TokenEmailRequestDTOTest {

    @Test
    public void getter() {
        assertThat(new TokenEmailRequestDTO().getName(), nullValue());
    }

    @Test
    public void setter() {
        assertThat(new TokenEmailRequestDTO().setName(""), notNullValue());
    }

}
