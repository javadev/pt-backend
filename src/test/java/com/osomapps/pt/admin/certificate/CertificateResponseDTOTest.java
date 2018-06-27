package com.osomapps.pt.admin.certificate;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class CertificateResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new CertificateResponseDTO(null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        CertificateResponseDTO certificateResponseDTO = new CertificateResponseDTO();
        certificateResponseDTO.setId(null);
        certificateResponseDTO.setCode(null);
        certificateResponseDTO.setAmountOfDays(null);
        certificateResponseDTO.setActivated(null);
        assertThat(certificateResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        CertificateResponseDTO certificateResponseDTO = new CertificateResponseDTO();
        certificateResponseDTO.getId();
        certificateResponseDTO.getCode();
        certificateResponseDTO.getAmountOfDays();
        certificateResponseDTO.getActivated();
        assertThat(certificateResponseDTO, notNullValue());
    }
}
