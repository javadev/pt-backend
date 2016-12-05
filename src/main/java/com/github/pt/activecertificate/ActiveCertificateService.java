package com.github.pt.activecertificate;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.admin.certificate.Certificate;
import com.github.pt.admin.certificate.CertificateRepository;
import com.github.pt.token.InUserLogin;
import com.github.pt.user.UserService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ActiveCertificateService {
    private final InUserCertificateRepository inUserCertificateRepository;
    private final CertificateRepository certificateRepository;
    private final UserService userService;

    @Autowired
    ActiveCertificateService(InUserCertificateRepository inUserCertificateRepository,
            CertificateRepository certificateRepository,
            UserService userService) {
        this.inUserCertificateRepository = inUserCertificateRepository;
        this.certificateRepository = certificateRepository;
        this.userService = userService;
    }

    ActiveCertificateResponseDTO firstActive(String token) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            return inUserLogin.getInUser().getInUserCertificates().stream()
                    .filter(inUserCertificate -> inUserCertificate.getCreated().toLocalDate()
                            .plusDays(inUserCertificate.getAmount_of_days()).isAfter(LocalDate.now()))
                    .map(inUserCertificate ->
                ActiveCertificateResponseDTO.builder()
                    .id(inUserCertificate.getId())
                    .code(inUserCertificate.getCode())
                    .expiration_date(inUserCertificate.getCreated().toLocalDate().plusDays(inUserCertificate.getAmount_of_days()))
                    .build()
            ).findFirst().orElse(new ActiveCertificateResponseDTO());
        }
        return new ActiveCertificateResponseDTO();
    }

    ActiveCertificateResponseDTO create(String token, ActiveCertificateRequestDTO certificateRequestDTO) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            final List<Certificate> certificates = certificateRepository.findByCode(certificateRequestDTO.getCode());
            if (certificates.isEmpty()) {
                throw new ResourceNotFoundException("Certificate with code " + certificateRequestDTO.getCode() + " not found.");
            }
            final Certificate notActiveCertificate = certificates.stream().filter(
                    certificate -> !certificate.getActivated()).findAny().orElseThrow(() ->
                        new ResourceNotFoundException("There is no active certificates with code " + certificateRequestDTO.getCode() + ".")
                    );
            notActiveCertificate.setActivated(Boolean.TRUE);
            InUserCertificate inUserCertificate = new InUserCertificate();
            inUserCertificate.setInUser(inUserLogin.getInUser());
            inUserCertificate.setCode(notActiveCertificate.getCode());
            inUserCertificate.setAmount_of_days(notActiveCertificate.getAmount_of_days());
            InUserCertificate inUserCertificateSaved = inUserCertificateRepository.save(inUserCertificate);
            certificateRepository.save(notActiveCertificate);
            return ActiveCertificateResponseDTO.builder()
                    .id(inUserCertificateSaved.getId())
                    .code(inUserCertificateSaved.getCode())
                    .expiration_date(LocalDate.now().plusDays(inUserCertificateSaved.getAmount_of_days()))
                    .build();
        }
        return new ActiveCertificateResponseDTO();
    }
}
