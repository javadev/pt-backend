package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PtUserTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtUser(1L, null, null, null, null, null,
                null, null, null, null, null, null, null, null,
                null, null, null, null, null), notNullValue());
    }
}
