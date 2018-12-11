package com.osomapps.pt.activecertificate;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActiveCertificateResourceTest {

    @Mock
    private ActiveCertificateService activeCertificateService;
    
    @InjectMocks
    private ActiveCertificateResource activeCertificateResource;

    @Test
    public void create() {
        activeCertificateResource.create("", new ActiveCertificateRequestDTO());
        verify(activeCertificateService).create(anyString(), any(ActiveCertificateRequestDTO.class));
    }

    public void list() throws Exception {
        activeCertificateResource.firstActive("");
        verify(activeCertificateService).firstActive(anyString());
    }
}
