package com.osomapps.pt.admin.email;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EmailMessageTemplateRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new EmailMessageTemplateRequestDTO(null, null, null, null, null), notNullValue());
    }
}
