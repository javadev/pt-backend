package com.osomapps.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise-output")
class AdminExerciseOutputResource {

    private final AdminExerciseOutputService exerciseOutputService;

    @Autowired
    AdminExerciseOutputResource(AdminExerciseOutputService exerciseOutputService) {
        this.exerciseOutputService = exerciseOutputService;
    }

    @GetMapping
    List<ExerciseOutputResponseDTO> findAll() {
        return exerciseOutputService.findAll();
    }
}
