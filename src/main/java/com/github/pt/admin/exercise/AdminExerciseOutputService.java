package com.github.pt.admin.exercise;

import com.github.pt.exercises.ExerciseOutput;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseOutputService {

    private final ExerciseOutputRepository exerciseOutputRepository;

    AdminExerciseOutputService(ExerciseOutputRepository exerciseOutputRepository) {
        this.exerciseOutputRepository = exerciseOutputRepository;
    }

    List<ExerciseOutputResponseDTO> findAll() {
        return exerciseOutputRepository.findAll().stream().map(AdminExerciseOutputService::exerciseOutputToDto
        ).collect(Collectors.toList());
    }
    
    private static ExerciseOutputResponseDTO exerciseOutputToDto(ExerciseOutput output) {
        return ExerciseOutputResponseDTO.builder()
                .id(output.getId())
                .name(output.getName())
                .build();
    }
}
