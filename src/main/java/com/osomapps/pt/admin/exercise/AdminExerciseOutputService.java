package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseOutput;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseOutputService {

    private final ExerciseOutputRepository exerciseOutputRepository;

    AdminExerciseOutputService(ExerciseOutputRepository exerciseOutputRepository) {
        this.exerciseOutputRepository = exerciseOutputRepository;
    }

    List<ExerciseOutputResponseDTO> findAll() {
        return exerciseOutputRepository.findAll(sortByIdAsc()).stream().map(AdminExerciseOutputService::exerciseOutputToDto
        ).collect(Collectors.toList());
    }
    
    private Sort sortByIdAsc() {
        return Sort.by(Sort.Direction.ASC, "id");
    }

    private static ExerciseOutputResponseDTO exerciseOutputToDto(ExerciseOutput output) {
        return ExerciseOutputResponseDTO.builder()
                .id(output.getId())
                .name(output.getName())
                .build();
    }
}
