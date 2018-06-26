package com.osomapps.pt.reportphoto;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PhotoRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PhotoRequestDTO(1L, ""), notNullValue());
    }
}
