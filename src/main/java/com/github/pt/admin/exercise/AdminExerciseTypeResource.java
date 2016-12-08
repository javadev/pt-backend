package com.github.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise-type")
class AdminExerciseTypeResource {

    private final AdminExerciseTypeService exerciseTypeService;

    @Autowired
    AdminExerciseTypeResource(AdminExerciseTypeService exerciseTypeService) {
        this.exerciseTypeService = exerciseTypeService;
    }

    @GetMapping
    List<ExerciseTypeResponseDTO> findAll() {
        return exerciseTypeService.findAll();
    }
}
