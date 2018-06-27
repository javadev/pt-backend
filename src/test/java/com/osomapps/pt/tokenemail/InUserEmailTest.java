package com.osomapps.pt.tokenemail;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class InUserEmailTest {
    @Test
    public void create() {
        assertThat(new InUserEmail(), notNullValue());
    }

    @Test
    public void createAllArgs() {
        assertThat(new InUserEmail(
                1L,
                null,
                LocalDateTime.now(),
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null), notNullValue());
    }

    @Test
    public void setters() {
        InUserEmail inUserEmail = new InUserEmail();
        inUserEmail.setId(1L);
        inUserEmail.setCreated(LocalDateTime.MAX);
        inUserEmail.setDevice_id("");
        inUserEmail.setIs_confirmed(null);
        inUserEmail.setConfirmed(null);
        inUserEmail.setConfirmToken("");
        inUserEmail.setIs_reseted(null);
        inUserEmail.setReseted(null);
        inUserEmail.setResetToken("");
        assertThat(inUserEmail, notNullValue());
    }

    @Test
    public void getters() {
        InUserEmail inUserEmail = new InUserEmail();
        inUserEmail.getId();
        inUserEmail.getCreated();
        inUserEmail.getDevice_id();
        inUserEmail.getIs_confirmed();
        inUserEmail.getConfirmed();
        inUserEmail.getConfirmToken();
        inUserEmail.getIs_reseted();
        inUserEmail.getReseted();
        inUserEmail.getResetToken();
        assertThat(inUserEmail, notNullValue());
    }
}
