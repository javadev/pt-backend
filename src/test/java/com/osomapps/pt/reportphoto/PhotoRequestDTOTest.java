package com.osomapps.pt.reportphoto;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PhotoRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PhotoRequestDTO(1L, ""), notNullValue());
    }
}
