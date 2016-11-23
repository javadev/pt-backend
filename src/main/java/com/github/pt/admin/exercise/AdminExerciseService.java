package com.github.pt.admin.exercise;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.Exercise;
import com.github.pt.exercises.ExerciseCategory;
import com.github.pt.exercises.ExerciseRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final DictionaryService dictionaryService;
    
    AdminExerciseService(ExerciseRepository exerciseRepository,
            ExerciseCategoryRepository exerciseCategoryRepository,
            ExerciseTypeRepository exerciseTypeRepository,
            DictionaryService dictionaryService) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.dictionaryService = dictionaryService;
    }
    
    List<ExerciseResponseDTO> findAll() {
        return exerciseRepository.findAll(sortByIdAsc()).stream().map(exercise ->
            exerciseToDto(exercise)
        ).collect(Collectors.toList());
    }
    
    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "id");
    }

    private ExerciseResponseDTO exerciseToDto(Exercise exercise) {
        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .exerciseId(exercise.getExercise_id())
                .nameEn(dictionaryService.getEnValue(DictionaryName.exercise_name, exercise.getDExerciseName(), ""))
                .nameNo(dictionaryService.getNoValue(DictionaryName.exercise_name, exercise.getDExerciseName(), ""))
                .descriptionEn(dictionaryService.getEnValue(DictionaryName.exercise_description, exercise.getDExerciseDescription(), ""))
                .descriptionNo(dictionaryService.getNoValue(DictionaryName.exercise_description, exercise.getDExerciseDescription(), ""))
                .category(ExerciseCategoryResponseDTO.builder()
                        .id(exercise.getExerciseCategory().getId())
                        .nameEn(dictionaryService.getEnValue(
                                DictionaryName.exercise_category_name, exercise.getExerciseCategory().getDExerciseCategoryName(), ""))
                        .nameNo(dictionaryService.getNoValue(
                                DictionaryName.exercise_category_name, exercise.getExerciseCategory().getDExerciseCategoryName(), ""))
                        .build())
                .types(exercise.getExerciseTypes().stream()
                    .map(type -> ExerciseTypeResponseDTO.builder()
                        .id(type.getId())
                        .nameEn(dictionaryService.getEnValue(
                                DictionaryName.exercise_type_name, type.getD_exercise_type_name(), "")
                        )
                        .build())
                        .collect(Collectors.toList()))
                .build();
    }

    ExerciseResponseDTO findOne(Long id) {
        final Exercise exercise = exerciseRepository.findOne(id);
        if (exercise == null) {
            throw new ResourceNotFoundException("Exercise with id " + id + " not found.");
        }
        return exerciseToDto(exercise);
    }

    ExerciseResponseDTO create(ExerciseRequestDTO exerciseRequestDTO) {
        final ExerciseCategory exerciseCategoryDb = exerciseCategoryRepository
            .findOne(exerciseRequestDTO.getCategory().getId());
        if (exerciseCategoryDb == null) {
            throw new ResourceNotFoundException("Category not found in database: "
                    + exerciseRequestDTO.getCategory().getId());
        }
        final String dataKey = dictionaryService.getNewDictionaryDataKey(DictionaryName.exercise_name);
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_name, dataKey,
                exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final String dataDescriptionKey = dictionaryService.getNewDictionaryDataKey(DictionaryName.exercise_description);
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_description, dataDescriptionKey,
                exerciseRequestDTO.getDescriptionEn(), exerciseRequestDTO.getDescriptionNo());
        final Exercise exercise = new Exercise();
        exercise.setDExerciseName(dataKey);
        exercise.setDExerciseDescription(dataDescriptionKey);
        exercise.setExercise_id(exerciseRequestDTO.getExerciseId());
        exercise.setExerciseCategory(exerciseCategoryDb);
        exercise.setExerciseTypes(exerciseTypeRepository.findAll(
            exerciseRequestDTO.getTypes().stream().map(type -> type.getId()).collect(Collectors.toList())));
        return exerciseToDto(exerciseRepository.save(exercise));
    }

    ExerciseResponseDTO update(Long id, ExerciseRequestDTO exerciseRequestDTO) {
        final Exercise existedExercise = exerciseRepository.findOne(id);
        if (existedExercise == null) {
            throw new ResourceNotFoundException("Exercise with id not found: " + id);
        }
        final String dataKey = existedExercise.getDExerciseName();
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_name, dataKey,
                exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final String dataDescriptionKey = existedExercise.getDExerciseDescription() == null
                ? dictionaryService.getNewDictionaryDataKey(DictionaryName.exercise_description)
                : existedExercise.getDExerciseDescription();
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_description,
                dataDescriptionKey, exerciseRequestDTO.getDescriptionEn(),
                exerciseRequestDTO.getDescriptionNo());
        final ExerciseCategory exerciseCategoryDb = exerciseCategoryRepository
            .findOne(exerciseRequestDTO.getCategory().getId());
        if (exerciseCategoryDb == null) {
            throw new ResourceNotFoundException("Category not found in database: "
                    + exerciseRequestDTO.getCategory().getId());
        }
        existedExercise.setExerciseCategory(exerciseCategoryDb);
        existedExercise.setDExerciseName(dataKey);
        existedExercise.setDExerciseDescription(dataDescriptionKey);
        existedExercise.setExercise_id(exerciseRequestDTO.getExerciseId());
        existedExercise.setExerciseTypes(exerciseTypeRepository.findAll(
                exerciseRequestDTO.getTypes().stream().map(type -> type.getId()).collect(Collectors.toList())));
        final Exercise savedExercise = exerciseRepository.save(existedExercise);
        return exerciseToDto(savedExercise);
    }

    ExerciseResponseDTO delete(Long id) {
        final Exercise exercise = exerciseRepository.findOne(id);
        if (exercise == null) {
            throw new ResourceNotFoundException("Exercise with id " + id + " not found.");
        }
        dictionaryService.deleteDatas(DictionaryName.exercise_name, exercise.getDExerciseName());
        dictionaryService.deleteDatas(DictionaryName.exercise_description, exercise.getDExerciseDescription());
        final ExerciseResponseDTO exerciseResponseDTO = exerciseToDto(exercise);
        exerciseRepository.delete(id);
        return exerciseResponseDTO;
    }
}
