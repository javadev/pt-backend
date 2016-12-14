package com.osomapps.pt.goals;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/goals")
class GoalsResource {

    private final GoalService goalService;

    @Autowired
    GoalsResource(GoalService goalService) {
        this.goalService = goalService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<GoalDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return goalService.findAll(token);
    }
}
