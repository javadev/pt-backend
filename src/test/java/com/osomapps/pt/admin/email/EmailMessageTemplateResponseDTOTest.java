package com.osomapps.pt.admin.email;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class EmailMessageTemplateResponseDTOTest {
    @Test
    public void createAllArgs() {
        assertThat(new EmailMessageTemplateResponseDTO(null, null, null, null, null, null), notNullValue());
    }

    @Test
    public void noArgsConstructor() {
        assertThat(new EmailMessageTemplateResponseDTO(), notNullValue());
    }

    @Test
    public void setters() {
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = new EmailMessageTemplateResponseDTO()
            .setId(null)
            .setEmailSubjectEn(null)
            .setEmailSubjectNo(null)
            .setEmailTextEn(null)
            .setEmailTextNo(null);
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

    @Test
    public void getters() {
        EmailMessageTemplateResponseDTO emailMessageTemplateResponseDTO = new EmailMessageTemplateResponseDTO();
        emailMessageTemplateResponseDTO.getId();
        emailMessageTemplateResponseDTO.getEmailSubjectEn();
        emailMessageTemplateResponseDTO.getEmailSubjectNo();
        emailMessageTemplateResponseDTO.getEmailTextEn();
        emailMessageTemplateResponseDTO.getEmailTextNo();
        assertThat(emailMessageTemplateResponseDTO, notNullValue());
    }

}
