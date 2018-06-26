package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PtUserRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtUserRequestDTO(
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null), notNullValue());
    }
}
