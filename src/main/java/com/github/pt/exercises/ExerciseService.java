package com.github.pt.exercises;

import com.github.pt.dictionary.DictionaryData;
import com.github.pt.dictionary.DictionaryRepository;
import com.github.pt.user.UserService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final DictionaryRepository dictionaryRepository;
    private final UserService userService;

    @Autowired
    ExerciseService(ExerciseRepository exerciseRepository,
            DictionaryRepository dictionaryRepository,
            UserService userService) {
        this.exerciseRepository = exerciseRepository;
        this.dictionaryRepository = dictionaryRepository;
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
            List<DictionaryData> exerciseNames = dictionaryRepository.
                    findDictionaryValue(DictionaryRepository.EXERCISE_NAME, exercise.getDExerciseName(),
                            LocalDateTime.now());
            exerciseDTO.setName(exerciseNames.get(0).getDvalue());
            ExerciseCategoryDTO categoryDTO = new ExerciseCategoryDTO();
            categoryDTO.setId(Long.parseLong(exercise.getExerciseCategory().getDExerciseCategoryName()));
            List<DictionaryData> exerciseCategoryNames = dictionaryRepository.
                    findDictionaryValue(DictionaryRepository.EXERCISE_CATEGORY_NAME,
                            exercise.getExerciseCategory().getDExerciseCategoryName(), LocalDateTime.now());
            categoryDTO.setName(exerciseCategoryNames.get(0).getDvalue());
            exerciseDTO.setCategory(categoryDTO);
            return exerciseDTO;
        }).collect(Collectors.toList());
    }
}
