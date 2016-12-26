package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.Exercise;
import com.osomapps.pt.exercises.ExerciseBodypart;
import com.osomapps.pt.exercises.ExerciseEquipmentType;
import com.osomapps.pt.exercises.ExerciseFile;
import com.osomapps.pt.exercises.ExerciseFileRepository;
import com.osomapps.pt.exercises.ExerciseRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.MapBindingResult;

@Service
class AdminExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseBodypartRepository exerciseBodypartRepository;
    private final ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final DictionaryService dictionaryService;
    private final ExerciseInputRepository exerciseInputRepository;
    private final ExerciseOutputRepository exerciseOutputRepository;
    private final ExerciseFileRepository exerciseFileRepository;
    private final DataurlValidator dataurlValidator;

    AdminExerciseService(ExerciseRepository exerciseRepository,
            ExerciseBodypartRepository exerciseBodypartRepository,
            ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository,
            ExerciseTypeRepository exerciseTypeRepository,
            DictionaryService dictionaryService,
            ExerciseInputRepository exerciseInputRepository,
            ExerciseOutputRepository exerciseOutputRepository,
            ExerciseFileRepository exerciseFileRepository,
            DataurlValidator dataurlValidator) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseBodypartRepository = exerciseBodypartRepository;
        this.exerciseEquipmentTypeRepository = exerciseEquipmentTypeRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.dictionaryService = dictionaryService;
        this.exerciseInputRepository = exerciseInputRepository;
        this.exerciseOutputRepository = exerciseOutputRepository;
        this.exerciseFileRepository = exerciseFileRepository;
        this.dataurlValidator = dataurlValidator;
    }

    List<ExerciseResponseDTO> findAll() {
        return exerciseRepository.findAll(sortByIdAsc()).stream().map(exercise ->
            exerciseToDto(exercise)
        ).collect(Collectors.toList());
    }

    private Sort sortByIdAsc() {
        return new Sort(Sort.Direction.ASC, "exerciseId");
    }

    private ExerciseResponseDTO exerciseToDto(Exercise exercise) {
        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .exerciseId(exercise.getExerciseId())
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
                .types(exercise.getExerciseTypes().stream()
                    .map(type -> ExerciseTypeResponseDTO.builder()
                        .id(type.getId())
                        .name(type.getName())
                        .build())
                        .collect(Collectors.toList()))
                .inputs(exercise.getExerciseInputs().stream()
                    .map(input -> ExerciseInputResponseDTO.builder()
                        .id(input.getId())
                        .name(input.getName())
                        .build())
                        .collect(Collectors.toList()))
                .outputs(exercise.getExerciseOutputs().stream()
                    .map(output -> ExerciseOutputResponseDTO.builder()
                        .id(output.getId())
                        .name(output.getName())
                        .build())
                        .collect(Collectors.toList()))
                .files(exercise.getExerciseFiles().stream()
                    .map(file -> ExerciseFileResponseDTO.builder()
                        .id(file.getId())
                        .file_name(file.getFile_name())
                        .file_size(file.getFile_size())
                        .file_type(file.getFile_type())
                        .data_url(file.getData_url())
                        .build())
                        .collect(Collectors.toList()))
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
                exerciseRequestDTO.getBodypart() == null || exerciseRequestDTO.getBodypart().getId() == null
                ? null : exerciseBodypartRepository.findOne(exerciseRequestDTO.getBodypart().getId());
        final ExerciseEquipmentType exerciseEquipmentTypeDb =
            exerciseRequestDTO.getEquipmentType() == null || exerciseRequestDTO.getEquipmentType().getId() == null
                ? null : exerciseEquipmentTypeRepository.findOne(exerciseRequestDTO.getEquipmentType().getId());
        final String dataKey = dictionaryService.getNewDictionaryDataKey(DictionaryName.exercise_name);
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_name, dataKey,
                exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final String dataDescriptionKey = dictionaryService.getNewDictionaryDataKey(DictionaryName.exercise_description);
        dictionaryService.createDictionaryDataKey(DictionaryName.exercise_description, dataDescriptionKey,
                exerciseRequestDTO.getDescriptionEn(), exerciseRequestDTO.getDescriptionNo());
        final Exercise exercise = new Exercise();
        exercise.setDExerciseName(dataKey);
        exercise.setDExerciseDescription(dataDescriptionKey);
        exercise.setExerciseId(exerciseRequestDTO.getExerciseId());
        exercise.setExerciseBodypart(exerciseBodypartDb);
        exercise.setExerciseEquipmentType(exerciseEquipmentTypeDb);
        exercise.setExerciseTypes(exerciseTypeRepository.findAll(
            exerciseRequestDTO.getTypes().stream().map(type -> type.getId()).collect(Collectors.toList())));
        if (exerciseRequestDTO.getInputs() != null) {
            exercise.setExerciseInputs(exerciseInputRepository.findAll(
                exerciseRequestDTO.getInputs().stream().map(input -> input.getId()).collect(Collectors.toList())));
        }
        if (exerciseRequestDTO.getOutputs() != null) {
            exercise.setExerciseOutputs(exerciseOutputRepository.findAll(
                exerciseRequestDTO.getOutputs().stream().map(output -> output.getId()).collect(Collectors.toList())));
        }
        if (exerciseRequestDTO.getFiles() != null) {
            exercise.setExerciseFiles(exerciseRequestDTO.getFiles().stream().map(file -> {
                    final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
                    dataurlValidator.validate(file.getData_url(), errors);
                    if (errors.hasErrors()) {
                        throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
                    }
                    return new ExerciseFile()
                            .setFile_name(file.getFile_name())
                            .setFile_size(file.getFile_size())
                            .setFile_type(file.getFile_type())
                            .setData_url(file.getData_url());
            }).collect(Collectors.toList()));
            exerciseFileRepository.save(exercise.getExerciseFiles());
        }
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
        existedExercise.setExerciseBodypart(exerciseBodypartDb);
        existedExercise.setExerciseEquipmentType(exerciseEquipmentTypeDb);
        existedExercise.setDExerciseName(dataKey);
        existedExercise.setDExerciseDescription(dataDescriptionKey);
        existedExercise.setExerciseId(exerciseRequestDTO.getExerciseId());
        existedExercise.setCardio_percent(exerciseRequestDTO.getCardioPercent());
        existedExercise.setExerciseTypes(exerciseTypeRepository.findAll(
                exerciseRequestDTO.getTypes().stream().map(type -> type.getId()).collect(Collectors.toList())));
        if (exerciseRequestDTO.getInputs() != null) {
            existedExercise.setExerciseInputs(exerciseInputRepository.findAll(
                exerciseRequestDTO.getInputs().stream().map(input -> input.getId()).collect(Collectors.toList())));
        }
        if (exerciseRequestDTO.getOutputs() != null) {
            existedExercise.setExerciseOutputs(exerciseOutputRepository.findAll(
                exerciseRequestDTO.getOutputs().stream().map(output -> output.getId()).collect(Collectors.toList())));
        }
        if (exerciseRequestDTO.getFiles() != null) {
            exerciseFileRepository.delete(existedExercise.getExerciseFiles());
            existedExercise.setExerciseFiles(exerciseRequestDTO.getFiles().stream().map(file -> {
                    final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
                    dataurlValidator.validate(file.getData_url(), errors);
                    if (errors.hasErrors()) {
                        throw new UnauthorizedException(errors.getAllErrors().get(0).getDefaultMessage());
                    }
                    return new ExerciseFile()
                            .setFile_name(file.getFile_name())
                            .setFile_size(file.getFile_size())
                            .setFile_type(file.getFile_type())
                            .setData_url(file.getData_url());
            }).collect(Collectors.toList()));
            exerciseFileRepository.save(existedExercise.getExerciseFiles());
        }
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
