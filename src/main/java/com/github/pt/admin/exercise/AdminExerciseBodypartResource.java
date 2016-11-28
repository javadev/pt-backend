package com.github.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise-bodypart")
class AdminExerciseBodypartResource {

    private final AdminExerciseBodypartService exerciseBodypartService;
    
    @Autowired
    AdminExerciseBodypartResource(AdminExerciseBodypartService exerciseBodypart) {
        this.exerciseBodypartService = exerciseBodypart;
    }

    @GetMapping
    List<ExerciseBodypartResponseDTO> findAll() {
        return exerciseBodypartService.findAll();
    }
}
