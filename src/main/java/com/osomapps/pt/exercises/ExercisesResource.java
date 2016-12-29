package com.osomapps.pt.exercises;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/exercises")
class ExercisesResource {

    private final ExerciseService exerciseService;

    @Autowired
    ExercisesResource(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    List<ExerciseDTO> findAll(@RequestHeader(value = "X-Token") String token) {
        return exerciseService.findAll(token);
    }
}
