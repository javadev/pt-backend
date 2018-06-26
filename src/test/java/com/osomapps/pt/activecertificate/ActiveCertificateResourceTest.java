package com.osomapps.pt.activecertificate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
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
