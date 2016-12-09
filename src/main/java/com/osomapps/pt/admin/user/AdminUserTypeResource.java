package com.osomapps.pt.admin.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user-type")
class AdminUserTypeResource {

    private final AdminUserTypeService userTypeService;
    
    @Autowired
    AdminUserTypeResource(AdminUserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping
    List<UserTypeResponseDTO> findAll() {
        return userTypeService.findAll();
    }
}
