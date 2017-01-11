package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ProgramResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ProgramResponseDTO(null, null), notNullValue());
    }
}
