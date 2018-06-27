package com.osomapps.pt.reportweight;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class WeightRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WeightRequestDTO(1L), notNullValue());
    }
}
