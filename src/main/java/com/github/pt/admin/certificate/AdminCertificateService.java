package com.github.pt.admin.certificate;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.UnauthorizedException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

@Service
class AdminCertificateService {
    
    private final CertificateRepository certificateRepository;
    private final CertificateValidator certificateValidator;
    private final AmountOfDaysValidator amountOfDaysValidator;
    
    AdminCertificateService(CertificateRepository certificateRepository,
            CertificateValidator certificateValidator,
            AmountOfDaysValidator amountOfDaysValidator) {
        this.certificateRepository = certificateRepository;
        this.certificateValidator = certificateValidator;
        this.amountOfDaysValidator = amountOfDaysValidator;
    }

    List<CertificateResponseDTO> findAll() {
        return certificateRepository.findAll().stream().map(AdminCertificateService::certifivateToDto)
        .collect(Collectors.toList());
    }

    private static CertificateResponseDTO certifivateToDto(Certificate certificate) {
        return CertificateResponseDTO.builder()
                .id(certificate.getId())
                .code(certificate.getCode())
                .amountOfDays(certificate.getAmount_of_days())
                .activated(certificate.getActivated())
                .build();
    }

    CertificateResponseDTO findOne(Long id) {
        final Certificate certificate = certificateRepository.findOne(id);
        if (certificate == null) {
            throw new ResourceNotFoundException("Certificate not found in database: "
                    + id);
        }
        return certifivateToDto(certificate);
    }

    CertificateResponseDTO create(CertificateRequestDTO certificateRequestDTO) {
        final MapBindingResult certificateErrors = new MapBindingResult(
                new HashMap<>(), String.class.getName());
        certificateValidator.validate(certificateRequestDTO.getCode(), certificateErrors);
        if (certificateErrors.hasErrors()) {
            throw new UnauthorizedException(certificateErrors.getAllErrors().get(0).getDefaultMessage());
        }
        final MapBindingResult amountOfDaysErrors = new MapBindingResult(
                new HashMap<>(), String.class.getName());
        amountOfDaysValidator.validate(certificateRequestDTO.getAmountOfDays(), amountOfDaysErrors);
        if (amountOfDaysErrors.hasErrors()) {
            throw new UnauthorizedException(amountOfDaysErrors.getAllErrors().get(0).getDefaultMessage());
        }
        final Certificate certificate = new Certificate();
        certificate.setCode(certificateRequestDTO.getCode());
        certificate.setAmount_of_days(certificateRequestDTO.getAmountOfDays());
        certificate.setActivated(Boolean.FALSE);
        return certifivateToDto(certificateRepository.save(certificate));
    }

    CertificateResponseDTO update(Long id, CertificateRequestDTO certificateRequestDTO) {
        final Certificate certificate = certificateRepository.findOne(id);
        if (certificate == null) {
            throw new ResourceNotFoundException("Certificate not found in database: "
                    + id);
        }
        final MapBindingResult certificateErrors = new MapBindingResult(
                new HashMap<>(), String.class.getName());
        certificateValidator.validate(certificateRequestDTO.getCode(), certificateErrors);
        if (certificateErrors.hasErrors()) {
            throw new UnauthorizedException(certificateErrors.getAllErrors().get(0).getDefaultMessage());
        }
        final MapBindingResult amountOfDaysErrors = new MapBindingResult(
                new HashMap<>(), String.class.getName());
        amountOfDaysValidator.validate(certificateRequestDTO.getAmountOfDays(), amountOfDaysErrors);
        if (amountOfDaysErrors.hasErrors()) {
            throw new UnauthorizedException(amountOfDaysErrors.getAllErrors().get(0).getDefaultMessage());
        }
        certificate.setCode(certificateRequestDTO.getCode());
        certificate.setAmount_of_days(certificateRequestDTO.getAmountOfDays());
        return certifivateToDto(certificateRepository.save(certificate));
    }

    CertificateResponseDTO delete(Long id) {
        final Certificate certificate = certificateRepository.findOne(id);
        if (certificate == null) {
            throw new ResourceNotFoundException("Certificate not found in database: "
                    + id);
        }
        final CertificateResponseDTO certificateResponseDTO = certifivateToDto(certificate);
        certificateRepository.delete(certificate);
        return certificateResponseDTO;
    }
    
}
