package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class PtRoleRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtRoleRequestDTO(1L), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new PtRoleRequestDTO(), notNullValue());
    }

    @Test
    public void setters() {
        PtRoleRequestDTO ptRoleRequestDTO = new PtRoleRequestDTO();
        ptRoleRequestDTO.setId(null);
        assertThat(ptRoleRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        PtRoleRequestDTO ptRoleRequestDTO = new PtRoleRequestDTO();
        ptRoleRequestDTO.getId();
        assertThat(ptRoleRequestDTO, notNullValue());
    }
}
