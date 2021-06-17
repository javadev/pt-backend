package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class PtRoleResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtRoleResponseDTO(1L, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new PtRoleResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        PtRoleResponseDTO ptRoleResponseDTO = new PtRoleResponseDTO();
        ptRoleResponseDTO.setId(null);
        ptRoleResponseDTO.setName(null);
        assertThat(ptRoleResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        PtRoleResponseDTO ptRoleResponseDTO = new PtRoleResponseDTO();
        ptRoleResponseDTO.getId();
        ptRoleResponseDTO.getName();
        assertThat(ptRoleResponseDTO, notNullValue());
    }
}
