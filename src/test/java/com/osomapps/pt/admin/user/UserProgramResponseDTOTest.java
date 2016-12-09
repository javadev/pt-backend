package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserProgramResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new UserProgramResponseDTO(1L, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserProgramResponseDTO userProgramResponseDTO = new UserProgramResponseDTO();
        userProgramResponseDTO.setId(null);
        userProgramResponseDTO.setName(null);
        userProgramResponseDTO.setType(null);
        userProgramResponseDTO.setCreated(null);
        assertThat(userProgramResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserProgramResponseDTO userProgramResponseDTO = new UserProgramResponseDTO();
        userProgramResponseDTO.getId();
        userProgramResponseDTO.getName();
        userProgramResponseDTO.getType();
        userProgramResponseDTO.getCreated();
        assertThat(userProgramResponseDTO, notNullValue());
    }
}
