package com.osomapps.pt.admin.email;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminEmailMessageTemplateResourceTest {

    @Mock
    private AdminEmailMessageTemplateService adminEmailMessageTemplateService;

    @InjectMocks
    private AdminEmailMessageTemplateResource adminEmailMessageTemplateResource;

    @Test
    public void findAll() {
        adminEmailMessageTemplateResource.findAll();
        verify(adminEmailMessageTemplateService).findAll();
    }

    @Test
    public void findOne() {
        adminEmailMessageTemplateResource.findOne(1L);
        verify(adminEmailMessageTemplateService).findOne(anyLong());
    }

    @Test
    public void create() {
        adminEmailMessageTemplateResource.create(new EmailMessageTemplateRequestDTO());
        verify(adminEmailMessageTemplateService).create(any(EmailMessageTemplateRequestDTO.class));
    }

    @Test
    public void update() {
        adminEmailMessageTemplateResource.update(1L, new EmailMessageTemplateRequestDTO());
        verify(adminEmailMessageTemplateService).update(anyLong(), any(EmailMessageTemplateRequestDTO.class));
    }

    @Test
    public void delete() {
        adminEmailMessageTemplateResource.delete(1L);
        verify(adminEmailMessageTemplateService).delete(anyLong());
    }

}
