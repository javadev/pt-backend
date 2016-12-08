package com.github.pt.admin.certificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

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
