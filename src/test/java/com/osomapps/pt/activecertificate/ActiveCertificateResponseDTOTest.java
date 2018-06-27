package com.osomapps.pt.activecertificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActiveCertificateResponseDTOTest {
    @Test
    public void setters() {
        ActiveCertificateResponseDTO activeCertificateResponseDTO = new ActiveCertificateResponseDTO();
        activeCertificateResponseDTO.setCode(null).setExpiration_date(null);
        assertThat(activeCertificateResponseDTO, notNullValue());
    }
}
