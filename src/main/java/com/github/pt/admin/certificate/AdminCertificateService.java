package com.github.pt.admin.certificate;

import com.github.pt.ResourceNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminCertificateService {
    
    private final CertificateRepository certificateRepository;
    
    AdminCertificateService(CertificateRepository certificateRepository) {
        this.certificateRepository = certificateRepository;
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
