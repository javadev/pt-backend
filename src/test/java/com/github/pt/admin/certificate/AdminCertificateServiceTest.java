package com.github.pt.admin.certificate;

import com.github.pt.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminCertificateServiceTest {
    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private CertificateValidator certificateValidator;
    @Mock
    private AmountOfDaysValidator amountOfDaysValidator;

    @InjectMocks
    private AdminCertificateService adminCertificateService;

    @Test
    public void findAll() {
        adminCertificateService.findAll();
        verify(certificateRepository).findAll();
    }

    @Test
    public void findOne() {
        when(certificateRepository.findOne(eq(1L))).thenReturn(new Certificate());
        adminCertificateService.findOne(1L);
        verify(certificateRepository).findOne(eq(1L));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_token_not_found() {
        when(certificateRepository.findOne(eq(2L))).thenReturn(new Certificate());
        adminCertificateService.findOne(1L);
    }

    @Test
    public void create() {
        when(certificateRepository.save(any(Certificate.class))).thenAnswer(i -> i.getArguments()[0]);
        adminCertificateService.create(new CertificateRequestDTO());
        verify(certificateRepository).save(any(Certificate.class));
    }

    @Test
    public void update() {
        when(certificateRepository.findOne(eq(1L))).thenReturn(new Certificate());
        when(certificateRepository.save(any(Certificate.class))).thenAnswer(i -> i.getArguments()[0]);
        adminCertificateService.update(1L, new CertificateRequestDTO());
        verify(certificateRepository).save(any(Certificate.class));
    }

    @Test
    public void delete() {
        when(certificateRepository.findOne(eq(1L))).thenReturn(new Certificate());
        adminCertificateService.delete(1L);
        verify(certificateRepository).delete(any(Certificate.class));
    }

}
