package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.ExerciseBodypart;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseBodypartService {

    private final ExerciseBodypartRepository exerciseBodypartRepository;
    private final DictionaryService dictionaryService;

    AdminExerciseBodypartService(
            ExerciseBodypartRepository exerciseBodypartRepository,
            DictionaryService dictionaryService) {
        this.exerciseBodypartRepository = exerciseBodypartRepository;
        this.dictionaryService = dictionaryService;
    }

    List<ExerciseBodypartResponseDTO> findAll() {
        return exerciseBodypartRepository.findAll().stream()
                .map(this::exerciseBodypartToDto)
                .collect(Collectors.toList());
    }

    private ExerciseBodypartResponseDTO exerciseBodypartToDto(ExerciseBodypart bodypart) {
        final String exerciseBodypartEnName =
                dictionaryService.getEnValue(
                        DictionaryName.exercise_bodypart_name,
                        bodypart.getDExerciseBodypartName(),
                        "");
        final String exerciseBodypartNoName =
                dictionaryService.getNoValue(
                        DictionaryName.exercise_bodypart_name,
                        bodypart.getDExerciseBodypartName(),
                        exerciseBodypartEnName);
        return ExerciseBodypartResponseDTO.builder()
                .id(bodypart.getId())
                .nameEn(exerciseBodypartEnName)
                .nameNo(exerciseBodypartNoName)
                .build();
    }
}
