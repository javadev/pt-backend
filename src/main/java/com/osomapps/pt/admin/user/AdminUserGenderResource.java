package com.osomapps.pt.admin.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user-gender")
class AdminUserGenderResource {

    private final AdminUserGenderService userGenderService;

    @Autowired
    AdminUserGenderResource(AdminUserGenderService userGenderService) {
        this.userGenderService = userGenderService;
    }

    @GetMapping
    List<UserGenderResponseDTO> findAll() {
        return userGenderService.findAll();
    }
}
