package com.osomapps.pt.admin.goal;

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
@RequestMapping("api/v1/admin/goal")
class AdminGoalResource {

    private final AdminGoalService adminGoalService;

    @Autowired
    AdminGoalResource(AdminGoalService adminGoalService) {
        this.adminGoalService = adminGoalService;
    }

    @GetMapping
    List<GoalResponseDTO> findAll() {
        return adminGoalService.findAll();
    }

    @GetMapping("{id}")
    GoalResponseDTO findOne(@PathVariable Long id) {
        return adminGoalService.findOne(id);
    }

    @PostMapping
    GoalResponseDTO create(@RequestBody GoalRequestDTO ptUserRequestDTO) {
        return adminGoalService.create(ptUserRequestDTO);
    }

    @PutMapping("{id}")
    GoalResponseDTO update(@PathVariable Long id, @RequestBody GoalRequestDTO ptUserRequestDTO) {
        return adminGoalService.update(id, ptUserRequestDTO);
    }

    @DeleteMapping("{id}")
    GoalResponseDTO delete(@PathVariable Long id) {
        return adminGoalService.delete(id);
    }

}
