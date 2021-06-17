package com.osomapps.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise-file")
class AdminExerciseFileResource {

    private final AdminExerciseFileService exerciseFileService;

    @Autowired
    AdminExerciseFileResource(AdminExerciseFileService exerciseFileService) {
        this.exerciseFileService = exerciseFileService;
    }

    @GetMapping("{ids}")
    List<ExerciseFileResponseDTO> findAll(@PathVariable List<Long> ids) {
        return exerciseFileService.findAll(ids);
    }
}
