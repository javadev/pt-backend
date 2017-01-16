package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserTypeResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserTypeResponseDTO(1L, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserTypeResponseDTO userTypeResponseDTO = new UserTypeResponseDTO();
        userTypeResponseDTO.setId(null);
        userTypeResponseDTO.setNameEn(null);
        userTypeResponseDTO.setNameNo(null);
        assertThat(userTypeResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserTypeResponseDTO userTypeResponseDTO = new UserTypeResponseDTO();
        userTypeResponseDTO.getId();
        userTypeResponseDTO.getNameEn();
        userTypeResponseDTO.getNameNo();
        assertThat(userTypeResponseDTO, notNullValue());
    }

    @Test
    public void builder() {
        UserTypeResponseDTO userTypeResponseDTO = UserTypeResponseDTO.builder().build();
        assertThat(userTypeResponseDTO, notNullValue());
    }
}
