package com.osomapps.pt.admin.program;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.ExerciseRepository;
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
                        .setExerciseId(exercise.getExerciseId())
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
