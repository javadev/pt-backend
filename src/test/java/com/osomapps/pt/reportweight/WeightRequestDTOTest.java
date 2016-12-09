package com.osomapps.pt.reportweight;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class WeightRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WeightRequestDTO(1L), notNullValue());
    }
}
