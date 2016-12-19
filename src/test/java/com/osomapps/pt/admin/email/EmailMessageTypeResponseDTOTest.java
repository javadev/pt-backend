package com.osomapps.pt.admin.email;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EmailMessageTypeResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new EmailMessageTypeResponseDTO(null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new EmailMessageTypeResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        EmailMessageTypeResponseDTO emailMessageTypeResponseDTO = new EmailMessageTypeResponseDTO()
            .setId(null)
            .setName(null);
        assertThat(emailMessageTypeResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        EmailMessageTypeResponseDTO emailMessageTypeResponseDTO = new EmailMessageTypeResponseDTO();
        emailMessageTypeResponseDTO.getId();
        emailMessageTypeResponseDTO.getName();
        assertThat(emailMessageTypeResponseDTO, notNullValue());
    }

}
