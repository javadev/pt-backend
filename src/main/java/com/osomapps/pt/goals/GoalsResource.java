package com.osomapps.pt.goals;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/goals")
class GoalsResource {

    private final GoalService goalService;

    @Autowired
    GoalsResource(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    List<GoalDTO> findAll() {
        return goalService.findAll();
    }
}
