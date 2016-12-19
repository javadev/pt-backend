package com.osomapps.pt.email;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EmailMessageTypeTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new EmailMessageType(null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        assertThat(new EmailMessageType().setId(null), notNullValue());
        assertThat(new EmailMessageType().setCreated(null), notNullValue());
        assertThat(new EmailMessageType().setName(null), notNullValue());
        assertThat(new EmailMessageType().setEmailMessageTemplates(null), notNullValue());
    }

    @Test
    public void getters() {
        assertThat(new EmailMessageType().getId(), nullValue());
        assertThat(new EmailMessageType().getCreated(), nullValue());
        assertThat(new EmailMessageType().getName(), nullValue());
        assertThat(new EmailMessageType().getEmailMessageTemplates(), nullValue());
    }
}
