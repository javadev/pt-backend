package com.github.pt.activatecertificate;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.admin.certificate.Certificate;
import com.github.pt.admin.certificate.CertificateRepository;
import com.github.pt.token.InUserLogin;
import com.github.pt.user.UserService;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ActivateCertificateService {
    private final InUserCertificateRepository InUserCertificateRepository;
    private final CertificateRepository certificateRepository;
    private final UserService userService;

    @Autowired
    ActivateCertificateService(InUserCertificateRepository inUserCertificateRepository,
            CertificateRepository certificateRepository,
            UserService userService) {
        this.InUserCertificateRepository = inUserCertificateRepository;
        this.certificateRepository = certificateRepository;
        this.userService = userService;
    }

    List<ActivateCertificateResponseDTO> findAll(String token) {
        if (!token.isEmpty()) {
            final InUserLogin inUserLogin = userService.checkUserToken(token);
            return inUserLogin.getInUser().getInUserCertificates().stream().map(inUserCertificate ->
                ActivateCertificateResponseDTO.builder()
                    .id(inUserCertificate.getId())
                    .code(inUserCertificate.getCode())
                    .build()
            ).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    ActivateCertificateResponseDTO create(String token, ActivateCertificateRequestDTO certificateRequestDTO) {
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
            InUserCertificate inUserCertificateSaved = InUserCertificateRepository.save(inUserCertificate);
            certificateRepository.save(notActiveCertificate);
            return ActivateCertificateResponseDTO.builder()
                    .id(inUserCertificateSaved.getId())
                    .code(inUserCertificateSaved.getCode())
                    .expiration_date(LocalDate.now().plusDays(inUserCertificateSaved.getAmount_of_days()))
                    .build();
        }
        return new ActivateCertificateResponseDTO();
    }
}
