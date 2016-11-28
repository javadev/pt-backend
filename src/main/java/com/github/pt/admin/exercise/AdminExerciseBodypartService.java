package com.github.pt.admin.exercise;

import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.ExerciseBodypart;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseBodypartService {

    private final ExerciseBodypartRepository exerciseBodypartRepository;
    private final DictionaryService dictionaryService;

    AdminExerciseBodypartService(ExerciseBodypartRepository exerciseBodypartRepository,
            DictionaryService dictionaryService) {
        this.exerciseBodypartRepository = exerciseBodypartRepository;
        this.dictionaryService = dictionaryService;
    }

    List<ExerciseBodypartResponseDTO> findAll() {
        return exerciseBodypartRepository.findAll().stream().map(bodypart ->
            exerciseBodypartToDto(bodypart)
        ).collect(Collectors.toList());
    }
    
    private ExerciseBodypartResponseDTO exerciseBodypartToDto(ExerciseBodypart bodypart) {
        final String exerciseBodypartEnName = dictionaryService.getEnValue(DictionaryName.exercise_bodypart_name,
                        bodypart.getDExerciseBodypartName(), "");
        final String exerciseBodypartNoName = dictionaryService.getNoValue(DictionaryName.exercise_bodypart_name,
                        bodypart.getDExerciseBodypartName(), exerciseBodypartEnName);
        return ExerciseBodypartResponseDTO.builder()
                .id(bodypart.getId())
                .nameEn(exerciseBodypartEnName)
                .nameNo(exerciseBodypartNoName)
                .build();
    }
}
