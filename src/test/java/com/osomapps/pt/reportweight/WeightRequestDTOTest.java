package com.osomapps.pt.reportweight;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class WeightRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new WeightRequestDTO(1L), notNullValue());
    }
}
