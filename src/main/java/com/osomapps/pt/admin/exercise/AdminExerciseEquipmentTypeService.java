package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.ExerciseEquipmentType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseEquipmentTypeService {

    private final ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository;
    private final DictionaryService dictionaryService;

    AdminExerciseEquipmentTypeService(
            ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository,
            DictionaryService dictionaryService) {
        this.exerciseEquipmentTypeRepository = exerciseEquipmentTypeRepository;
        this.dictionaryService = dictionaryService;
    }

    List<ExerciseEquipmentTypeResponseDTO> findAll() {
        return exerciseEquipmentTypeRepository.findAll().stream()
                .map(equipmentType -> exerciseEquipmentTypeToDto(equipmentType))
                .collect(Collectors.toList());
    }

    private ExerciseEquipmentTypeResponseDTO exerciseEquipmentTypeToDto(
            ExerciseEquipmentType equipmentType) {
        final String exerciseEquipmentTypeEnName =
                dictionaryService.getEnValue(
                        DictionaryName.exercise_equipment_type_name,
                        equipmentType.getDExerciseEquipmentTypeName(),
                        "");
        final String exerciseEquipmentTypeNoName =
                dictionaryService.getNoValue(
                        DictionaryName.exercise_equipment_type_name,
                        equipmentType.getDExerciseEquipmentTypeName(),
                        exerciseEquipmentTypeEnName);
        return ExerciseEquipmentTypeResponseDTO.builder()
                .id(equipmentType.getId())
                .nameEn(exerciseEquipmentTypeEnName)
                .nameNo(exerciseEquipmentTypeNoName)
                .build();
    }
}
