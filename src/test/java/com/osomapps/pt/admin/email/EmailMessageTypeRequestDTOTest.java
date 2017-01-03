package com.osomapps.pt.admin.email;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EmailMessageTypeRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new EmailMessageTypeRequestDTO(null), notNullValue());
    }

    @Test
    public void builder() {
        assertThat(EmailMessageTypeRequestDTO.builder().build(), notNullValue());
    }
}
