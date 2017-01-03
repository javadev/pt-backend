package com.osomapps.pt.admin.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user-level")
class AdminUserLevelResource {

    private final AdminUserLevelService userLevelService;

    @Autowired
    AdminUserLevelResource(AdminUserLevelService userLevelService) {
        this.userLevelService = userLevelService;
    }

    @GetMapping
    List<UserLevelResponseDTO> findAll() {
        return userLevelService.findAll();
    }
}
