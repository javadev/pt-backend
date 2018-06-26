package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ProgramResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ProgramResponseDTO(null, null), notNullValue());
    }
}
