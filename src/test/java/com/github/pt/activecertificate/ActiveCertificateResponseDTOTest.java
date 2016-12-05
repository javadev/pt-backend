package com.github.pt.activecertificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class ActiveCertificateResponseDTOTest {
    @Test
    public void setters() {
        ActiveCertificateResponseDTO activeCertificateResponseDTO = new ActiveCertificateResponseDTO();
        activeCertificateResponseDTO.setCode(null).setExpiration_date(null);
        assertThat(activeCertificateResponseDTO, notNullValue());
    }
}
