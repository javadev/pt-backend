package com.github.pt.admin.exercise;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/admin/exercise-equipment-type")
class AdminExerciseEquipmentTypeResource {

    private final AdminExerciseEquipmentTypeService exerciseEquipmentTypeService;
    
    @Autowired
    AdminExerciseEquipmentTypeResource(AdminExerciseEquipmentTypeService exerciseEquipmentTypeService) {
        this.exerciseEquipmentTypeService = exerciseEquipmentTypeService;
    }

    @GetMapping
    List<ExerciseEquipmentTypeResponseDTO> findAll() {
        return exerciseEquipmentTypeService.findAll();
    }
}
