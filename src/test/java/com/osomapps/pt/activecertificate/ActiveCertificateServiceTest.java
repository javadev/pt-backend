package com.osomapps.pt.activecertificate;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.admin.certificate.Certificate;
import com.osomapps.pt.admin.certificate.CertificateRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserLogin;
import com.osomapps.pt.user.UserService;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ActiveCertificateServiceTest {
    @Mock
    private InUserCertificateRepository inUserCertificateRepository;
    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private UserService userService;

    @InjectMocks
    private ActiveCertificateService activeCertificateService;

    @Test
    public void firstActive() {
        assertThat(activeCertificateService.firstActive("").getCode(),
                equalTo(new ActiveCertificateResponseDTO().getCode()));
    }

    @Test
    public void firstActive_with_token() {
        InUserCertificate inUserCertificate = new InUserCertificate();
        inUserCertificate.setCreated(LocalDateTime.MAX.minusDays(1));
        inUserCertificate.setAmount_of_days(1);
        InUser inUser = new InUser();
        inUser.setInUserCertificates(Arrays.asList(inUserCertificate));
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(inUser);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        assertThat(activeCertificateService.firstActive("1").getCode(),
                equalTo(new ActiveCertificateResponseDTO().getCode()));
    }

    @Test
    public void create() {
        ActiveCertificateRequestDTO activeCertificateRequestDTO = new ActiveCertificateRequestDTO();
        assertThat(activeCertificateService.create("", activeCertificateRequestDTO).getCode(),
            equalTo(new ActiveCertificateResponseDTO().getCode()));
    }

    @Test
    public void create_with_token() {
        InUserCertificate inUserCertificate = new InUserCertificate();
        inUserCertificate.setId(1L);
        inUserCertificate.setCreated(LocalDateTime.MIN);
        inUserCertificate.setAmount_of_days(1);
        InUser inUser = new InUser();
        inUser.setInUserCertificates(Arrays.asList(inUserCertificate));
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(inUser);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(certificateRepository.findByCode(eq("123"))).thenReturn(
            Arrays.asList(new Certificate().setActivated(Boolean.FALSE).setAmount_of_days(1)));
        when(inUserCertificateRepository.save(any(InUserCertificate.class))).thenAnswer(i -> i.getArguments()[0]);
        ActiveCertificateRequestDTO activeCertificateRequestDTO = new ActiveCertificateRequestDTO().setCode("123");
        assertThat(activeCertificateService.create("1", activeCertificateRequestDTO).getCode(),
            equalTo(new ActiveCertificateResponseDTO().getCode()));
    }

    @Test
    public void create_with_token_code_not_found() {
        InUserCertificate inUserCertificate = new InUserCertificate();
        inUserCertificate.setId(1L);
        inUserCertificate.setCreated(LocalDateTime.MIN);
        inUserCertificate.setAmount_of_days(1);
        InUser inUser = new InUser();
        inUser.setInUserCertificates(Arrays.asList(inUserCertificate));
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(inUser);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(certificateRepository.findByCode(eq("123"))).thenReturn(
            Collections.emptyList());
        ActiveCertificateRequestDTO activeCertificateRequestDTO = new ActiveCertificateRequestDTO().setCode("123");
        assertThrows(ResourceNotFoundException.class, () -> {activeCertificateService.create("1", activeCertificateRequestDTO);});
    }

    @Test
    public void create_with_token_no_active_certificates() {
        InUserCertificate inUserCertificate = new InUserCertificate();
        inUserCertificate.setId(1L);
        inUserCertificate.setCreated(LocalDateTime.MIN);
        inUserCertificate.setAmount_of_days(1);
        InUser inUser = new InUser();
        inUser.setInUserCertificates(Arrays.asList(inUserCertificate));
        InUserLogin inUserLogin = new InUserLogin();
        inUserLogin.setInUser(inUser);
        when(userService.checkUserToken(eq("1"))).thenReturn(inUserLogin);
        when(certificateRepository.findByCode(eq("123"))).thenReturn(
            Arrays.asList(new Certificate().setActivated(Boolean.TRUE).setAmount_of_days(1)));
        when(inUserCertificateRepository.save(any(InUserCertificate.class))).thenAnswer(i -> i.getArguments()[0]);
        ActiveCertificateRequestDTO activeCertificateRequestDTO = new ActiveCertificateRequestDTO().setCode("123");
        assertThrows(ResourceNotFoundException.class, () -> {activeCertificateService.create("1", activeCertificateRequestDTO).getCode();});
    }

}
