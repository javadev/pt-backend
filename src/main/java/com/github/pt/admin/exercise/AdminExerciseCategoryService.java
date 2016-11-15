package com.github.pt.admin.exercise;

import com.github.pt.dictionary.DictionaryData;
import com.github.pt.dictionary.DictionaryRepository;
import com.github.pt.exercises.ExerciseCategory;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseCategoryService {

    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final DictionaryRepository dictionaryRepository;

    AdminExerciseCategoryService(ExerciseCategoryRepository exerciseCategoryRepository,
            DictionaryRepository dictionaryRepository) {
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.dictionaryRepository = dictionaryRepository;
    }

    List<ExerciseCategoryResponseDTO> findAll() {
        return exerciseCategoryRepository.findAll().stream().map(category ->
            exerciseCategoryToDto(category)
        ).collect(Collectors.toList());
    }
    
    private ExerciseCategoryResponseDTO exerciseCategoryToDto(ExerciseCategory category) {
        final List<DictionaryData> exerciseCategoryEnNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_CATEGORY_NAME,
                        category.getDExerciseCategoryName(), LocalDateTime.now());
        final List<DictionaryData> exerciseCategoryNoNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE, DictionaryRepository.EXERCISE_CATEGORY_NAME,
                        category.getDExerciseCategoryName(), LocalDateTime.now());
        return ExerciseCategoryResponseDTO.builder()
                .id(category.getId())
                .nameEn(exerciseCategoryEnNames.get(0).getDvalue())
                .nameNo(exerciseCategoryNoNames.get(0).getDvalue())
                .build();
    }
}
