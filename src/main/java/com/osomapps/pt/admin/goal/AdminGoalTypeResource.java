package com.osomapps.pt.admin.goal;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/goal-type")
class AdminGoalTypeResource {

    private final AdminGoalTypeService adminGoalTypeService;

    @Autowired
    AdminGoalTypeResource(AdminGoalTypeService adminGoalTypeService) {
        this.adminGoalTypeService = adminGoalTypeService;
    }

    @GetMapping
    List<GoalTypeResponseDTO> findAll() {
        return adminGoalTypeService.findAll();
    }
}
