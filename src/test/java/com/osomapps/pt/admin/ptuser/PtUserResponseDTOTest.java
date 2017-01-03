package com.osomapps.pt.admin.ptuser;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class PtUserResponseDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new PtUserResponseDTO(
                1L,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new PtUserResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        PtUserResponseDTO ptUserResponseDTO = new PtUserResponseDTO();
        ptUserResponseDTO.setId(null);
        ptUserResponseDTO.setCreated(null);
        ptUserResponseDTO.setLogin(null);
        ptUserResponseDTO.setName(null);
        ptUserResponseDTO.setActivated(null);
        ptUserResponseDTO.setIs_blocked(null);
        ptUserResponseDTO.setIs_blocked_date_set(null);
        ptUserResponseDTO.setBlocked_comment(null);
        ptUserResponseDTO.setBlocked_start(null);
        ptUserResponseDTO.setBlocked_finish(null);
        ptUserResponseDTO.setDeleted(null);
        ptUserResponseDTO.setDeleted_comment(null);
        ptUserResponseDTO.setIs_deleted(null);
        ptUserResponseDTO.setIs_default_password(null);
        ptUserResponseDTO.setDescription(null);
        ptUserResponseDTO.setPhone(null);
        ptUserResponseDTO.setPhone2(null);
        ptUserResponseDTO.setRoles(null);
        assertThat(ptUserResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        PtUserResponseDTO ptUserResponseDTO = new PtUserResponseDTO();
        ptUserResponseDTO.getId();
        ptUserResponseDTO.getCreated();
        ptUserResponseDTO.getLogin();
        ptUserResponseDTO.getName();
        ptUserResponseDTO.getActivated();
        ptUserResponseDTO.getIs_blocked();
        ptUserResponseDTO.getIs_blocked_date_set();
        ptUserResponseDTO.getBlocked_comment();
        ptUserResponseDTO.getBlocked_start();
        ptUserResponseDTO.getBlocked_finish();
        ptUserResponseDTO.getDeleted();
        ptUserResponseDTO.getDeleted_comment();
        ptUserResponseDTO.getIs_deleted();
        ptUserResponseDTO.getIs_default_password();
        ptUserResponseDTO.getDescription();
        ptUserResponseDTO.getPhone();
        ptUserResponseDTO.getPhone2();
        ptUserResponseDTO.getRoles();
        assertThat(ptUserResponseDTO, notNullValue());
    }

}
