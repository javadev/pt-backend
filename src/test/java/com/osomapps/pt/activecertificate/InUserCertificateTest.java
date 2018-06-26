package com.osomapps.pt.activecertificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class InUserCertificateTest {
    @Test
    public void createAllArgs() {
        assertThat(new InUserCertificate(
                1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserCertificate inUserCertificate = new InUserCertificate();
        inUserCertificate.setInUser(null);
        assertThat(inUserCertificate, notNullValue());
    }

    @Test
    public void getters() {
        InUserCertificate inUserCertificate = new InUserCertificate();
        inUserCertificate.getInUser();
        assertThat(inUserCertificate, notNullValue());
    }
}
