package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class UserProgramRequestDTOTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new UserProgramRequestDTO(null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        UserProgramRequestDTO userProgramRequestDTO = new UserProgramRequestDTO();
        userProgramRequestDTO.setName(null);
        userProgramRequestDTO.setType(null);
        userProgramRequestDTO.setWorkouts(null);
        assertThat(userProgramRequestDTO, notNullValue());
    }

    @Test
    public void getters() {
        UserProgramRequestDTO userProgramRequestDTO = new UserProgramRequestDTO();
        userProgramRequestDTO.getName();
        userProgramRequestDTO.getType();
        userProgramRequestDTO.getWorkouts();
        assertThat(userProgramRequestDTO, notNullValue());
    }

}
