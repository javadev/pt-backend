package com.github.pt.admin.program;

import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.ExerciseRepository;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminProgramScanExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final DictionaryService dictionaryService;

    AdminProgramScanExerciseService(ExerciseRepository exerciseRepository,
            DictionaryService dictionaryService) {
        this.exerciseRepository = exerciseRepository;
        this.dictionaryService = dictionaryService;
    }

    Optional<Long> getExerciseIdByName(String name) {
        Optional<ExerciseResponse> exerciseResponseOptional = exerciseRepository.findAll(sortByIdAsc()).stream()
                .map(exercise -> new ExerciseResponse()
                        .setId(exercise.getId())
                        .setExerciseId(exercise.getExercise_id())
                        .setNameEn(dictionaryService.getEnValue(DictionaryName.exercise_name, exercise.getDExerciseName(), ""))
                ).filter(exerciseResponse -> 
                        exerciseResponse.getNameEn().trim().equalsIgnoreCase(name.trim())
                )
                .findFirst();
        return exerciseResponseOptional.map(exerciseResponse -> exerciseResponse.getExerciseId());
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

}
