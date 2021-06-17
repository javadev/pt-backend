package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.exercises.ExerciseFileRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseFileService {
    private final ExerciseFileRepository exerciseFileRepository;

    AdminExerciseFileService(ExerciseFileRepository exerciseFileRepository) {
        this.exerciseFileRepository = exerciseFileRepository;
    }

    List<ExerciseFileResponseDTO> findAll(List<Long> ids) {
        return exerciseFileRepository.findAllById(ids).stream()
                .map(
                        file ->
                                ExerciseFileResponseDTO.builder()
                                        .id(file.getId())
                                        .file_name(file.getFile_name())
                                        .file_size(file.getFile_size())
                                        .file_type(file.getFile_type())
                                        .data_url(file.getData_url())
                                        .build())
                .collect(Collectors.toList());
    }
}
