package com.osomapps.pt.email;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EmailMessageTemplateTest {

    @Test
    public void allArgsConstructor() {
        assertThat(new EmailMessageTemplate(null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        assertThat(new EmailMessageTemplate().setId(null), notNullValue());
        assertThat(new EmailMessageTemplate().setCreated(null), notNullValue());
        assertThat(new EmailMessageTemplate().setEmailMessageType(null), notNullValue());
    }

    @Test
    public void getters() {
        assertThat(new EmailMessageTemplate().getId(), nullValue());
        assertThat(new EmailMessageTemplate().getCreated(), nullValue());
        assertThat(new EmailMessageTemplate().getEmailMessageType(), nullValue());
    }
}
