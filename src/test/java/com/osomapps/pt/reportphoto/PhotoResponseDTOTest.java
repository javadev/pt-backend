package com.osomapps.pt.reportphoto;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PhotoResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PhotoResponseDTO(null, ""), notNullValue());
    }

    @Test
    public void setters() {
        PhotoResponseDTO photoResponseDTO = new PhotoResponseDTO();
        photoResponseDTO.setDate(LocalDateTime.MAX);
        photoResponseDTO.setPhoto(null);
        assertThat(photoResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        PhotoResponseDTO photoResponseDTO = new PhotoResponseDTO();
        photoResponseDTO.getDate();
        photoResponseDTO.getPhoto();
        assertThat(photoResponseDTO, notNullValue());
    }
}
