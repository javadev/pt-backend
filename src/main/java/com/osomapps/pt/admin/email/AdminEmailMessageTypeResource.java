package com.osomapps.pt.admin.email;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/email-message-type")
class AdminEmailMessageTypeResource {

    private final AdminEmailMessageTypeService adminEmailMessageTypeService;

    @Autowired
    AdminEmailMessageTypeResource(AdminEmailMessageTypeService adminEmailMessageTypeService) {
        this.adminEmailMessageTypeService = adminEmailMessageTypeService;
    }

    @GetMapping
    List<EmailMessageTypeResponseDTO> findAll() {
        return adminEmailMessageTypeService.findAll();
    }
}
