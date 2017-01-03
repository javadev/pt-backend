package com.osomapps.pt.admin.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/user-goal")
class AdminUserGoalResource {

    private final AdminUserGoalService userGoalService;

    @Autowired
    AdminUserGoalResource(AdminUserGoalService userGoalService) {
        this.userGoalService = userGoalService;
    }

    @GetMapping
    List<UserGoalResponseDTO> findAll() {
        return userGoalService.findAll();
    }
}
