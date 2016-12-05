package com.github.pt.activecertificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class ActiveCertificateRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new ActiveCertificateRequestDTO(null), notNullValue());
    }

    @Test
    public void setters() {
        ActiveCertificateRequestDTO activeCertificateRequestDTO = new ActiveCertificateRequestDTO();
        activeCertificateRequestDTO.setCode(null);
        assertThat(activeCertificateRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        ActiveCertificateRequestDTO activeCertificateRequestDTO = new ActiveCertificateRequestDTO();
        activeCertificateRequestDTO.getCode();
        assertThat(activeCertificateRequestDTO, notNullValue());
    }

}
