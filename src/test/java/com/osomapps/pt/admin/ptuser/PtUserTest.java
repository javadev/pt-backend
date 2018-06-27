package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PtUserTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtUser(1L, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null), notNullValue());
    }
}
