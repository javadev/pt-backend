package com.osomapps.pt.admin.certificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class CertificateTest {
    @Test
    public void createAllArgs() {
        assertThat(new Certificate(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        Certificate certificate = new Certificate();
        certificate.setCreated(null);
        assertThat(certificate, notNullValue());
    }

    @Test
    public void getters() {
        Certificate certificate = new Certificate();
        certificate.getCreated();
        assertThat(certificate, notNullValue());
    }
}
