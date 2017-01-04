package com.osomapps.pt.exercises;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final DictionaryService dictionaryService;

    @Autowired
    ExerciseService(ExerciseRepository exerciseRepository,
            DictionaryService dictionaryService) {
        this.exerciseRepository = exerciseRepository;
        this.dictionaryService = dictionaryService;
    }

    List<ExerciseDTO> findAll() {
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(exercise -> {
            ExerciseDTO exerciseDTO = new ExerciseDTO();
            exerciseDTO.setId(exercise.getExerciseId());
            exerciseDTO.setName(dictionaryService.getEnValue(DictionaryName.exercise_name,
                    exercise.getDExerciseName(), ""));
            exerciseDTO.setDescription(dictionaryService.getEnValue(DictionaryName.exercise_description,
                    exercise.getDExerciseDescription(), null));
            exerciseDTO.setCardio_percent(exercise.getCardio_percent());
            exerciseDTO.setTypes(exercise.getExerciseTypes()
                    .stream().map(type -> type.getName()).collect(Collectors.toList()));
            exerciseDTO.setImages(exercise.getExerciseFiles()
                    .stream().map(file -> "/api/v1/exercise-image/"
                            + file.getId() + "/" + file.getFile_name()).collect(Collectors.toList()));
            return exerciseDTO;
        }).sorted((e1, e2) -> Long.compare(e1.getId(), e2.getId()))
                .collect(Collectors.toList());
    }
}
