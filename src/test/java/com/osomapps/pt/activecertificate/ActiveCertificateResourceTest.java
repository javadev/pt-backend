package com.osomapps.pt.activecertificate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ActiveCertificateResourceTest {

    @Mock private ActiveCertificateService activeCertificateService;

    @InjectMocks private ActiveCertificateResource activeCertificateResource;

    @Test
    public void create() {
        activeCertificateResource.create("", new ActiveCertificateRequestDTO());
        verify(activeCertificateService)
                .create(anyString(), any(ActiveCertificateRequestDTO.class));
    }

    public void list() throws Exception {
        activeCertificateResource.firstActive("");
        verify(activeCertificateService).firstActive(anyString());
    }
}
