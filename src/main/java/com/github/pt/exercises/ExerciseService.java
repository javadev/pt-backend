package com.github.pt.exercises;

import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.user.UserService;
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
            exerciseDTO.setId(Long.parseLong(exercise.getDExerciseName()));
            exerciseDTO.setName(dictionaryService.getEnValue(DictionaryName.exercise_name,
                    exercise.getDExerciseName(), ""));
            ExerciseBodypartDTO bodypartDTO = new ExerciseBodypartDTO();
            bodypartDTO.setId(Long.parseLong(exercise.getExerciseBodypart().getDExerciseBodypartName()));
            bodypartDTO.setName(dictionaryService.getEnValue(DictionaryName.exercise_bodypart_name,
                    exercise.getExerciseBodypart().getDExerciseBodypartName(), ""));
            exerciseDTO.setBodypart(bodypartDTO);
            return exerciseDTO;
        }).collect(Collectors.toList());
    }
}
