package com.osomapps.pt.admin.certificate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/certificate")
class AdminCertificateResource {

    private final AdminCertificateService certificateService;
    
    @Autowired
    AdminCertificateResource(AdminCertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    List<CertificateResponseDTO> findAll() {
        return certificateService.findAll();
    }

    @GetMapping(value = "{id}")
    CertificateResponseDTO findOne(@PathVariable Long id) {
        return certificateService.findOne(id);
    }

    @PostMapping
    CertificateResponseDTO create(@RequestBody CertificateRequestDTO certificateRequestDTO) {
        return certificateService.create(certificateRequestDTO);
    }

    @PutMapping(value = "{id}")
    CertificateResponseDTO update(@PathVariable Long id, @RequestBody CertificateRequestDTO certificateRequestDTO) {
        return certificateService.update(id, certificateRequestDTO);
    }

    @DeleteMapping(value = "{id}")
    CertificateResponseDTO delete(@PathVariable Long id) {
        return certificateService.delete(id);
    }

}
