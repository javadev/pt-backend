package com.github.pt.activatecertificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/v1/activate-certificate")
class ActivateCertificateResource {

    private final ActivateCertificateService certificateService;
    
    @Autowired
    ActivateCertificateResource(ActivateCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    List<ActivateCertificateResponseDTO> list(@RequestHeader(value = "X-Token") String token) {
        return certificateService.findAll(token);
    }

    @PostMapping
    ActivateCertificateResponseDTO create(@RequestHeader(value = "X-Token") String token,
            @RequestBody ActivateCertificateRequestDTO certificateRequestDTO) {
        return certificateService.create(token, certificateRequestDTO);
    }
}
