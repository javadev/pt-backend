package com.osomapps.pt.exercises;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.user.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final DictionaryService dictionaryService;
    private final UserService userService;

    @Autowired
    ExerciseService(ExerciseRepository exerciseRepository,
            DictionaryService dictionaryService,
            UserService userService) {
        this.exerciseRepository = exerciseRepository;
        this.dictionaryService = dictionaryService;
        this.userService = userService;
    }

    List<ExerciseDTO> findAll(String token) {
        if (!token.isEmpty()) {
            userService.checkUserToken(token);
        }
        List<Exercise> exercises = exerciseRepository.findAll();
        return exercises.stream().map(exercise -> {
            ExerciseDTO exerciseDTO = new ExerciseDTO();
            exerciseDTO.setId(exercise.getExercise_id());
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
