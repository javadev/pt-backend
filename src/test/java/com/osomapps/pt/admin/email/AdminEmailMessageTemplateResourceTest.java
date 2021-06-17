package com.osomapps.pt.admin.email;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminEmailMessageTemplateResourceTest {

    @Mock private AdminEmailMessageTemplateService adminEmailMessageTemplateService;

    @InjectMocks private AdminEmailMessageTemplateResource adminEmailMessageTemplateResource;

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
        verify(adminEmailMessageTemplateService)
                .update(anyLong(), any(EmailMessageTemplateRequestDTO.class));
    }

    @Test
    public void delete() {
        adminEmailMessageTemplateResource.delete(1L);
        verify(adminEmailMessageTemplateService).delete(anyLong());
    }
}
