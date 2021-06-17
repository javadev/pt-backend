package com.osomapps.pt.reportphoto;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReportPhotoFileDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ReportPhotoFileDTO(1L, null, null), notNullValue());
    }

    @Test
    public void setters() {
        ReportPhotoFileDTO reportPhotoFileDTO = new ReportPhotoFileDTO();
        reportPhotoFileDTO.setId(1L);
        reportPhotoFileDTO.setFileName(null);
        assertThat(reportPhotoFileDTO, notNullValue());
    }

    @Test
    public void getters() {
        ReportPhotoFileDTO reportPhotoFileDTO = new ReportPhotoFileDTO();
        reportPhotoFileDTO.getId();
        reportPhotoFileDTO.getFileName();
        assertThat(reportPhotoFileDTO, notNullValue());
    }
}
