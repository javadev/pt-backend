package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseInput;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseInputService {

    private final ExerciseInputRepository exerciseInputRepository;

    AdminExerciseInputService(ExerciseInputRepository exerciseInputRepository) {
        this.exerciseInputRepository = exerciseInputRepository;
    }

    List<ExerciseInputResponseDTO> findAll() {
        return exerciseInputRepository.findAll().stream().map(
                AdminExerciseInputService::exerciseInputToDto
        ).collect(Collectors.toList());
    }
    
    private static ExerciseInputResponseDTO exerciseInputToDto(ExerciseInput input) {
        return ExerciseInputResponseDTO.builder()
                .id(input.getId())
                .name(input.getName())
                .build();
    }
}
