package com.github.pt.admin.exercise;

import com.github.pt.dictionary.DictionaryData;
import com.github.pt.dictionary.DictionaryRepository;
import com.github.pt.exercises.ExerciseType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseTypeService {

    private final ExerciseTypeRepository exerciseTypeRepository;
    private final DictionaryRepository dictionaryRepository;

    AdminExerciseTypeService(ExerciseTypeRepository exerciseTypeRepository,
            DictionaryRepository dictionaryRepository) {
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.dictionaryRepository = dictionaryRepository;
    }

    List<ExerciseTypeResponseDTO> findAll() {
        return exerciseTypeRepository.findAll().stream().map(category ->
            exerciseTypeToDto(category)
        ).collect(Collectors.toList());
    }
    
    private ExerciseTypeResponseDTO exerciseTypeToDto(ExerciseType type) {
        final List<DictionaryData> exerciseTypeEnNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_TYPE_NAME,
                        type.getD_exercise_type_name(), LocalDateTime.now());
        final List<DictionaryData> exerciseTypeNoNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE, DictionaryRepository.EXERCISE_TYPE_NAME,
                        type.getD_exercise_type_name(), LocalDateTime.now());
        return ExerciseTypeResponseDTO.builder()
                .id(type.getId())
                .nameEn(exerciseTypeEnNames.get(0).getDvalue())
                .nameNo(exerciseTypeNoNames.isEmpty() ? 
                        exerciseTypeEnNames.get(0).getDvalue()
                        : exerciseTypeNoNames.get(0).getDvalue())
                .build();
    }
}
