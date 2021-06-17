package com.osomapps.pt.activecertificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ActiveCertificateResponseDTOTest {
    @Test
    public void setters() {
        ActiveCertificateResponseDTO activeCertificateResponseDTO =
                new ActiveCertificateResponseDTO();
        activeCertificateResponseDTO.setCode(null).setExpiration_date(null);
        assertThat(activeCertificateResponseDTO, notNullValue());
    }
}
