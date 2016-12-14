package com.osomapps.pt.admin.goal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/goal-parameter")
class AdminGoalParameterResource {

    private final AdminGoalParameterService adminGoalParameterService;

    @Autowired
    AdminGoalParameterResource(AdminGoalParameterService adminGoalParameterService) {
        this.adminGoalParameterService = adminGoalParameterService;
    }

    @GetMapping
    List<GoalParameterResponseDTO> findAll() {
        return adminGoalParameterService.findAll();
    }
}
