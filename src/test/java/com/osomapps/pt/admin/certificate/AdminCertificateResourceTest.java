package com.osomapps.pt.admin.certificate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminCertificateResourceTest {

    @Mock
    private AdminCertificateService adminCertificateService;

    @InjectMocks
    private AdminCertificateResource adminCertificateResource;

    @Test
    public void findOne() {
        adminCertificateResource.findOne(1L);
        verify(adminCertificateService).findOne(anyLong());
    }

    @Test
    public void create() {
        adminCertificateResource.create(new CertificateRequestDTO());
        verify(adminCertificateService).create(any(CertificateRequestDTO.class));
    }

    @Test
    public void update() {
        adminCertificateResource.update(1L, new CertificateRequestDTO());
        verify(adminCertificateService).update(anyLong(), any(CertificateRequestDTO.class));
    }

    @Test
    public void delete() {
        adminCertificateResource.delete(1L);
        verify(adminCertificateService).delete(anyLong());
    }
}
