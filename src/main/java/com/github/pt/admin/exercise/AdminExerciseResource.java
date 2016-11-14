package com.github.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise")
class AdminExerciseResource {

    private final AdminExerciseService exerciseService;
    
    @Autowired
    AdminExerciseResource(AdminExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<ExerciseResponseDTO> findAll() {
        return exerciseService.findAll();
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    ExerciseResponseDTO findOne(@PathVariable Long id) {
        return exerciseService.findOne(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    ExerciseResponseDTO create(@RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        return exerciseService.create(exerciseRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    ExerciseResponseDTO update(@PathVariable Long id, @RequestBody ExerciseRequestDTO exerciseRequestDTO) {
        return exerciseService.update(id, exerciseRequestDTO);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    ExerciseResponseDTO delete(@PathVariable Long id) {
        return exerciseService.delete(id);
    }

}
