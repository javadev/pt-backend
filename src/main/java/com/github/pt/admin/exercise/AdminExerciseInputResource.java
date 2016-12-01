package com.github.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise-input")
class AdminExerciseInputResource {

    private final AdminExerciseInputService exerciseInputService;
    
    @Autowired
    AdminExerciseInputResource(AdminExerciseInputService exerciseInputService) {
        this.exerciseInputService = exerciseInputService;
    }

    @GetMapping
    List<ExerciseInputResponseDTO> findAll() {
        return exerciseInputService.findAll();
    }
}
