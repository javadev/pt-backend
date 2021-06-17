package com.osomapps.pt.admin.certificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CertificateRequestDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new CertificateRequestDTO(null, null), notNullValue());
    }

    @Test
    public void setters() {
        CertificateRequestDTO certificateRequestDTO = new CertificateRequestDTO();
        certificateRequestDTO.setCode(null);
        certificateRequestDTO.setAmountOfDays(null);
        assertThat(certificateRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        CertificateRequestDTO certificateRequestDTO = new CertificateRequestDTO();
        certificateRequestDTO.getCode();
        certificateRequestDTO.getAmountOfDays();
        assertThat(certificateRequestDTO, notNullValue());
    }

    @Test
    public void builder() {
        CertificateRequestDTO certificateRequestDTO = CertificateRequestDTO.builder().build();
        assertThat(certificateRequestDTO, notNullValue());
    }
}
