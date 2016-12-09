package com.osomapps.pt.activecertificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("api/v1/active-certificate")
class ActiveCertificateResource {

    private final ActiveCertificateService certificateService;
    
    @Autowired
    ActiveCertificateResource(ActiveCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    ActiveCertificateResponseDTO firstActive(@RequestHeader(value = "X-Token") String token) {
        return certificateService.firstActive(token);
    }

    @PostMapping
    ActiveCertificateResponseDTO create(@RequestHeader(value = "X-Token") String token,
            @RequestBody ActiveCertificateRequestDTO certificateRequestDTO) {
        return certificateService.create(token, certificateRequestDTO);
    }
}
