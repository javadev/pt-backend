package com.github.pt.admin.exercise;

import com.github.pt.ResourceNotFoundException;
import com.github.pt.dictionary.DictionaryData;
import com.github.pt.dictionary.DictionaryRepository;
import com.github.pt.exercises.Exercise;
import com.github.pt.exercises.ExerciseCategory;
import com.github.pt.exercises.ExerciseRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
class AdminExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseCategoryRepository exerciseCategoryRepository;
    private final ExerciseTypeRepository exerciseTypeRepository;
    private final DictionaryRepository dictionaryRepository;
    
    AdminExerciseService(ExerciseRepository exerciseRepository,
            ExerciseCategoryRepository exerciseCategoryRepository,
            ExerciseTypeRepository exerciseTypeRepository,
            DictionaryRepository dictionaryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
        this.exerciseTypeRepository = exerciseTypeRepository;
        this.dictionaryRepository = dictionaryRepository;
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
        final List<DictionaryData> exerciseEnNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE,
                        DictionaryRepository.EXERCISE_NAME, exercise.getDExerciseName(),
                        LocalDateTime.now());
        final List<DictionaryData> exerciseNoNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE,
                        DictionaryRepository.EXERCISE_NAME, exercise.getDExerciseName(),
                        LocalDateTime.now());
        final List<DictionaryData> exerciseEnDescriptions = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE,
                        DictionaryRepository.EXERCISE_DESCRIPTION, exercise.getDExerciseDescription(),
                        LocalDateTime.now());
        final List<DictionaryData> exerciseNoDescriptions = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE,
                        DictionaryRepository.EXERCISE_DESCRIPTION, exercise.getDExerciseDescription(),
                        LocalDateTime.now());
        List<DictionaryData> exerciseCategoryEnNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_CATEGORY_NAME,
                        exercise.getExerciseCategory().getDExerciseCategoryName(), LocalDateTime.now());
        List<DictionaryData> exerciseCategoryNoNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE, DictionaryRepository.EXERCISE_CATEGORY_NAME,
                        exercise.getExerciseCategory().getDExerciseCategoryName(), LocalDateTime.now());
        List<DictionaryData> exerciseTypeEnNames = dictionaryRepository.
                findDictionaryAllValues(DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_TYPE_NAME,
                        LocalDateTime.now());
        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .exerciseId(exercise.getExercise_id())
                .nameEn(exerciseEnNames.isEmpty() ? "" : exerciseEnNames.get(0).getDvalue())
                .nameNo(exerciseNoNames.isEmpty() ? "" : exerciseNoNames.get(0).getDvalue())
                .descriptionEn(exerciseEnDescriptions.isEmpty() ? "" : exerciseEnDescriptions.get(0).getDvalue())
                .descriptionNo(exerciseNoDescriptions.isEmpty() ? "" : exerciseNoDescriptions.get(0).getDvalue())
                .category(ExerciseCategoryResponseDTO.builder()
                        .id(exercise.getExerciseCategory().getId())
                        .nameEn(exerciseCategoryEnNames.get(0).getDvalue())
                        .nameNo(exerciseCategoryNoNames.isEmpty()
                                ? exerciseCategoryEnNames.get(0).getDvalue()
                                : exerciseCategoryNoNames.get(0).getDvalue())
                        .build())
                .types(exercise.getExerciseTypes().stream()
                    .map(type -> ExerciseTypeResponseDTO.builder()
                        .id(type.getId())
                        .nameEn(exerciseTypeEnNames.stream().filter(
                                data -> data.getDkey().equals(type.getD_exercise_type_name()))
                                .findFirst().get().getDvalue())
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
        final String dataKey = getNewDictionaryDataKey(DictionaryRepository.EXERCISE_NAME);
        createDictionaryDataKey(DictionaryRepository.EXERCISE_NAME, dataKey,
                exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final String dataDescriptionKey = getNewDictionaryDataKey(DictionaryRepository.EXERCISE_DESCRIPTION);
        createDictionaryDataKey(DictionaryRepository.EXERCISE_DESCRIPTION, dataDescriptionKey,
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

    private String getNewDictionaryDataKey(String dName) {
        final List<DictionaryData> allExerciseEnNames = dictionaryRepository.
                findDictionaryAllValues(DictionaryRepository.ENG_LANGUAGE,
                        dName, LocalDateTime.now());
        if (allExerciseEnNames == null || allExerciseEnNames.isEmpty()) {
            return "10";
        }
        final String biggestKey =  allExerciseEnNames.stream()
                .filter(data -> data.getDkey() != null && data.getDkey().matches("\\d+"))
                .sorted((d1, d2) ->
                        Integer.compare(Integer.parseInt(d2.getDkey()), Integer.parseInt(d1.getDkey())))
                    .findFirst().get().getDkey();
        return "" + (Integer.parseInt(biggestKey) + 10);
    }

    private void createDictionaryDataKey(String dName, String dKey, String exerciseNameEn, String exerciseNameNo) {
        final DictionaryData dataEn = new DictionaryData();
        dataEn.setDlanguage(DictionaryRepository.ENG_LANGUAGE);
        dataEn.setDname(dName);
        dataEn.setDkey(dKey);
        dataEn.setDvalue(exerciseNameEn);
        dictionaryRepository.save(dataEn);
        final DictionaryData dataNo = new DictionaryData();
        dataNo.setDlanguage(DictionaryRepository.NOR_LANGUAGE);
        dataNo.setDname(dName);
        dataNo.setDkey(dKey);
        dataNo.setDvalue(exerciseNameNo);
        dictionaryRepository.save(dataNo);
    }

    ExerciseResponseDTO update(Long id, ExerciseRequestDTO exerciseRequestDTO) {
        final Exercise existedExercise = exerciseRepository.findOne(id);
        if (existedExercise == null) {
            throw new ResourceNotFoundException("Exercise with id not found: " + id);
        }
        final String dataKey = existedExercise.getDExerciseName();
        createDictionaryDataKey(DictionaryRepository.EXERCISE_NAME, dataKey,
                exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final String dataDescriptionKey = existedExercise.getDExerciseDescription() == null
                ? getNewDictionaryDataKey(DictionaryRepository.EXERCISE_DESCRIPTION)
                : existedExercise.getDExerciseDescription();
        createDictionaryDataKey(DictionaryRepository.EXERCISE_DESCRIPTION,
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
        final List<DictionaryData> datasEng = dictionaryRepository.findDictionaryByKey(
                DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_NAME,
                exercise.getDExerciseName(), LocalDateTime.now());
        dictionaryRepository.delete(datasEng);
        final List<DictionaryData> datasNor = dictionaryRepository.findDictionaryByKey(
                DictionaryRepository.NOR_LANGUAGE, DictionaryRepository.EXERCISE_NAME,
                exercise.getDExerciseName(), LocalDateTime.now());
        dictionaryRepository.delete(datasNor);
        final List<DictionaryData> datasDescEng = dictionaryRepository.findDictionaryByKey(
                DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_DESCRIPTION,
                exercise.getDExerciseName(), LocalDateTime.now());
        dictionaryRepository.delete(datasDescEng);
        final List<DictionaryData> datasDescNor = dictionaryRepository.findDictionaryByKey(
                DictionaryRepository.NOR_LANGUAGE, DictionaryRepository.EXERCISE_DESCRIPTION,
                exercise.getDExerciseName(), LocalDateTime.now());
        dictionaryRepository.delete(datasDescNor);
        final ExerciseResponseDTO exerciseResponseDTO = exerciseToDto(exercise);
        exerciseRepository.delete(id);
        return exerciseResponseDTO;
    }
}
