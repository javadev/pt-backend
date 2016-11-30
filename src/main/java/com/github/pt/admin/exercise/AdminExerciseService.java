package com.github.pt.admin.exercise;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.dictionary.DictionaryName;
import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.Exercise;
import com.github.pt.exercises.ExerciseBodypart;
import com.github.pt.exercises.ExerciseEquipmentType;
import com.github.pt.exercises.ExerciseRepository;
import com.github.pt.exercises.ExerciseType;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseBodypartRepository exerciseBodypartRepository;
    private final ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final DictionaryService dictionaryService;
    
    AdminExerciseService(ExerciseRepository exerciseRepository,
            ExerciseBodypartRepository exerciseBodypartRepository,
            ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository,
            ExerciseTypeRepository exerciseTypeRepository,
            DictionaryService dictionaryService) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseBodypartRepository = exerciseBodypartRepository;
        this.exerciseEquipmentTypeRepository = exerciseEquipmentTypeRepository;
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
                .bodypart(exercise.getExerciseBodypart() == null ? null : ExerciseBodypartResponseDTO.builder()
                        .id(exercise.getExerciseBodypart().getId())
                        .nameEn(dictionaryService.getEnValue(DictionaryName.exercise_bodypart_name, exercise.getExerciseBodypart().getDExerciseBodypartName(), ""))
                        .nameNo(dictionaryService.getNoValue(DictionaryName.exercise_bodypart_name, exercise.getExerciseBodypart().getDExerciseBodypartName(), ""))
                        .build())
                .equipmentType(exercise.getExerciseEquipmentType() == null ? null : ExerciseEquipmentTypeResponseDTO.builder()
                        .id(exercise.getExerciseEquipmentType().getId())
                        .nameEn(dictionaryService.getEnValue(DictionaryName.exercise_equipment_type_name, exercise.getExerciseEquipmentType().getDExerciseEquipmentTypeName(), ""))
                        .nameNo(dictionaryService.getNoValue(DictionaryName.exercise_equipment_type_name, exercise.getExerciseEquipmentType().getDExerciseEquipmentTypeName(), ""))
                        .build())
                .type(exercise.getExerciseType() == null ? null : ExerciseTypeResponseDTO.builder()
                        .id(exercise.getExerciseType().getId())
                        .name(exercise.getExerciseType().getName())
                        .build())
                .cardioPercent(exercise.getCardio_percent())
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
        final ExerciseBodypart exerciseBodypartDb =
                exerciseRequestDTO.getBodypart() == null ? null : exerciseBodypartRepository
            .findOne(exerciseRequestDTO.getBodypart().getId());
        final ExerciseEquipmentType exerciseEquipmentTypeDb =
            exerciseRequestDTO.getEquipmentType() == null ? null : exerciseEquipmentTypeRepository
            .findOne(exerciseRequestDTO.getEquipmentType().getId());
        final ExerciseType exerciseTypeDb =
            exerciseRequestDTO.getType() == null ? null : exerciseTypeRepository
            .findOne(exerciseRequestDTO.getType().getId());
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
        exercise.setExerciseBodypart(exerciseBodypartDb);
        exercise.setExerciseEquipmentType(exerciseEquipmentTypeDb);
        exercise.setExerciseType(exerciseTypeDb);
        exercise.setCardio_percent(exerciseRequestDTO.getCardioPercent());
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
        final ExerciseBodypart exerciseBodypartDb =
                exerciseRequestDTO.getBodypart() == null ? null : exerciseBodypartRepository
            .findOne(exerciseRequestDTO.getBodypart().getId());
        final ExerciseEquipmentType exerciseEquipmentTypeDb =
            exerciseRequestDTO.getEquipmentType() == null ? null : exerciseEquipmentTypeRepository
            .findOne(exerciseRequestDTO.getEquipmentType().getId());
        final ExerciseType exerciseTypeDb =
            exerciseRequestDTO.getType() == null ? null : exerciseTypeRepository
            .findOne(exerciseRequestDTO.getType().getId());
        existedExercise.setExerciseBodypart(exerciseBodypartDb);
        existedExercise.setExerciseEquipmentType(exerciseEquipmentTypeDb);
        existedExercise.setExerciseType(exerciseTypeDb);
        existedExercise.setDExerciseName(dataKey);
        existedExercise.setDExerciseDescription(dataDescriptionKey);
        existedExercise.setExercise_id(exerciseRequestDTO.getExerciseId());
        existedExercise.setCardio_percent(exerciseRequestDTO.getCardioPercent());
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
