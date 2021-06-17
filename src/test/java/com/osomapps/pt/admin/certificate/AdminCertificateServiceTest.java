package com.osomapps.pt.admin.certificate;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.validation.Errors;

@RunWith(MockitoJUnitRunner.class)
public class AdminCertificateServiceTest {
    @Mock private CertificateRepository certificateRepository;
    @Mock private CertificateValidator certificateValidator;
    @Mock private AmountOfDaysValidator amountOfDaysValidator;

    @InjectMocks private AdminCertificateService adminCertificateService;

    @Test
    public void findAll() {
        adminCertificateService.findAll();
        verify(certificateRepository).findAll();
    }

    @Test
    public void findOne() {
        when(certificateRepository.findById(eq(1L))).thenReturn(Optional.of(new Certificate()));
        adminCertificateService.findOne(1L);
        verify(certificateRepository).findById(eq(1L));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_token_not_found() {
        adminCertificateService.findOne(1L);
    }

    @Test(expected = UnauthorizedException.class)
    public void create_certificateValidator() {
        doAnswer(
                        (Answer<Void>)
                                (InvocationOnMock invocation) -> {
                                    Object[] args = invocation.getArguments();
                                    ((Errors) args[1])
                                            .reject("certificate", "Invalid empty certificate");
                                    return null;
                                })
                .when(certificateValidator)
                .validate(anyObject(), any(Errors.class));
        adminCertificateService.create(new CertificateRequestDTO());
    }

    @Test(expected = UnauthorizedException.class)
    public void create_amountOfDaysValidator() {
        doAnswer(
                        (Answer<Void>)
                                (InvocationOnMock invocation) -> {
                                    Object[] args = invocation.getArguments();
                                    ((Errors) args[1])
                                            .reject("amountOfDays", "Invalid empty amountOfDays");
                                    return null;
                                })
                .when(amountOfDaysValidator)
                .validate(anyObject(), any(Errors.class));
        adminCertificateService.create(new CertificateRequestDTO());
    }

    @Test
    public void create() {
        when(certificateRepository.save(any(Certificate.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        adminCertificateService.create(new CertificateRequestDTO());
        verify(certificateRepository).save(any(Certificate.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminCertificateService.update(1L, new CertificateRequestDTO());
    }

    @Test
    public void update() {
        when(certificateRepository.findById(eq(1L))).thenReturn(Optional.of(new Certificate()));
        when(certificateRepository.save(any(Certificate.class)))
                .thenAnswer(i -> i.getArguments()[0]);
        adminCertificateService.update(1L, new CertificateRequestDTO());
        verify(certificateRepository).save(any(Certificate.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void update_certificateValidator() {
        when(certificateRepository.findById(eq(1L))).thenReturn(Optional.of(new Certificate()));
        doAnswer(
                        (Answer<Void>)
                                (InvocationOnMock invocation) -> {
                                    Object[] args = invocation.getArguments();
                                    ((Errors) args[1])
                                            .reject("certificate", "Invalid empty certificate");
                                    return null;
                                })
                .when(certificateValidator)
                .validate(anyObject(), any(Errors.class));
        adminCertificateService.update(1L, new CertificateRequestDTO());
    }

    @Test(expected = UnauthorizedException.class)
    public void update_amountOfDaysValidator() {
        when(certificateRepository.findById(eq(1L))).thenReturn(Optional.of(new Certificate()));
        doAnswer(
                        (Answer<Void>)
                                (InvocationOnMock invocation) -> {
                                    Object[] args = invocation.getArguments();
                                    ((Errors) args[1])
                                            .reject("amountOfDays", "Invalid empty amountOfDays");
                                    return null;
                                })
                .when(amountOfDaysValidator)
                .validate(anyObject(), any(Errors.class));
        adminCertificateService.update(1L, new CertificateRequestDTO());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminCertificateService.delete(1L);
    }

    @Test
    public void delete() {
        when(certificateRepository.findById(eq(1L))).thenReturn(Optional.of(new Certificate()));
        adminCertificateService.delete(1L);
        verify(certificateRepository).delete(any(Certificate.class));
    }
}
