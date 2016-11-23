package com.github.pt.admin.exercise;

import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.ExerciseType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseTypeService {

    private final ExerciseTypeRepository exerciseTypeRepository;
    private final DictionaryService dictionaryService;

    AdminExerciseTypeService(ExerciseTypeRepository exerciseTypeRepository,
            DictionaryService dictionaryService) {
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.dictionaryService = dictionaryService;
    }

    List<ExerciseTypeResponseDTO> findAll() {
        return exerciseTypeRepository.findAll().stream().map(category ->
            exerciseTypeToDto(category)
        ).collect(Collectors.toList());
    }
    
    private ExerciseTypeResponseDTO exerciseTypeToDto(ExerciseType type) {
        String nameEn = dictionaryService.getEnValue(DictionaryName.exercise_type_name, type.getD_exercise_type_name(), "");
        String nameNo = dictionaryService.getNoValue(DictionaryName.exercise_type_name, type.getD_exercise_type_name(), nameEn);
        return ExerciseTypeResponseDTO.builder()
                .id(type.getId())
                .nameEn(nameEn)
                .nameNo(nameNo)
                .build();
    }
}
