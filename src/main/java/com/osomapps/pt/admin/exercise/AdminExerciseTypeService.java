package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseTypeService {

    private final ExerciseTypeRepository exerciseTypeRepository;

    AdminExerciseTypeService(ExerciseTypeRepository exerciseTypeRepository) {
        this.exerciseTypeRepository = exerciseTypeRepository;
    }

    List<ExerciseTypeResponseDTO> findAll() {
        return exerciseTypeRepository.findAll().stream().map(AdminExerciseTypeService::exerciseTypeToDto
        ).collect(Collectors.toList());
    }
    
    private static ExerciseTypeResponseDTO exerciseTypeToDto(ExerciseType type) {
        return ExerciseTypeResponseDTO.builder()
                .id(type.getId())
                .name(type.getName())
                .build();
    }
}
