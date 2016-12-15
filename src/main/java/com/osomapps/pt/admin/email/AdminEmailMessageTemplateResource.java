package com.osomapps.pt.admin.email;

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
@RequestMapping("api/v1/admin/email-message-template")
class AdminEmailMessageTemplateResource {

    private final AdminEmailMessageTemplateService adminEmailMessageTemplateService;
    
    @Autowired
    AdminEmailMessageTemplateResource(AdminEmailMessageTemplateService adminEmailMessageTemplateService) {
        this.adminEmailMessageTemplateService = adminEmailMessageTemplateService;
    }

    @GetMapping
    List<EmailMessageTemplateResponseDTO> findAll() {
        return adminEmailMessageTemplateService.findAll();
    }

    @GetMapping(value = "{id}")
    EmailMessageTemplateResponseDTO findOne(@PathVariable Long id) {
        return adminEmailMessageTemplateService.findOne(id);
    }

    @PostMapping
    EmailMessageTemplateResponseDTO create(@RequestBody EmailMessageTemplateRequestDTO templateRequestDTO) {
        return adminEmailMessageTemplateService.create(templateRequestDTO);
    }

    @PutMapping(value = "{id}")
    EmailMessageTemplateResponseDTO update(@PathVariable Long id, @RequestBody EmailMessageTemplateRequestDTO templateRequestDTO) {
        return adminEmailMessageTemplateService.update(id, templateRequestDTO);
    }

    @DeleteMapping(value = "{id}")
    EmailMessageTemplateResponseDTO delete(@PathVariable Long id) {
        return adminEmailMessageTemplateService.delete(id);
    }

}
