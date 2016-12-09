package com.osomapps.pt.admin.exercise;

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
@RequestMapping("api/v1/admin/exercise")
class AdminExerciseResource {

    private final AdminExerciseService exerciseService;
    
    @Autowired
    AdminExerciseResource(AdminExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    List<ExerciseResponseDTO> findAll() {
        return exerciseService.findAll();
    }

    @GetMapping(value = "{id}")
    ExerciseResponseDTO findOne(@PathVariable Long id) {
        return exerciseService.findOne(id);
    }

    @PostMapping
    ExerciseResponseDTO create(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        return exerciseService.create(exerciseRequestDTO);
    }

    @PutMapping(value = "{id}")
    ExerciseResponseDTO update(@PathVariable Long id, @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        return exerciseService.update(id, exerciseRequestDTO);
    }

    @DeleteMapping(value = "{id}")
    ExerciseResponseDTO delete(@PathVariable Long id) {
        return exerciseService.delete(id);
    }

}
