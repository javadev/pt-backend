package com.github.pt.activatecertificate;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActivateCertificateResourceTest {

    @Mock
    private ActivateCertificateService activateCertificateService;
    
    @InjectMocks
    private ActivateCertificateResource activateCertificateResource;

    @Test
    public void create() {
        activateCertificateResource.create("", new ActivateCertificateRequestDTO());
        verify(activateCertificateService).create(anyString(), any(ActivateCertificateRequestDTO.class));
    }

    public void list() throws Exception {
        activateCertificateResource.list("");
        verify(activateCertificateService).findAll(anyString());
    }
}
