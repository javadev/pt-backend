package com.github.pt.admin.exercise;

import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.ExerciseCategory;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseCategoryService {

    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final DictionaryService dictionaryService;

    AdminExerciseCategoryService(ExerciseCategoryRepository exerciseCategoryRepository,
            DictionaryService dictionaryService) {
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.dictionaryService = dictionaryService;
    }

    List<ExerciseCategoryResponseDTO> findAll() {
        return exerciseCategoryRepository.findAll().stream().map(category ->
            exerciseCategoryToDto(category)
        ).collect(Collectors.toList());
    }
    
    private ExerciseCategoryResponseDTO exerciseCategoryToDto(ExerciseCategory category) {
        final String exerciseCategoryEnName = dictionaryService.getEnValue(DictionaryName.exercise_category_name,
                        category.getDExerciseCategoryName(), "");
        final String exerciseCategoryNoName = dictionaryService.getNoValue(DictionaryName.exercise_category_name,
                        category.getDExerciseCategoryName(), exerciseCategoryEnName);
        return ExerciseCategoryResponseDTO.builder()
                .id(category.getId())
                .nameEn(exerciseCategoryEnName)
                .nameNo(exerciseCategoryNoName)
                .build();
    }
}
