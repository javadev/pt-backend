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
    private final DictionaryRepository dictionaryRepository;
    
    AdminExerciseService(ExerciseRepository exerciseRepository,
            ExerciseCategoryRepository exerciseCategoryRepository,
            DictionaryRepository dictionaryRepository) {
        this.exerciseRepository = exerciseRepository;
        this.exerciseCategoryRepository = exerciseCategoryRepository;
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
        List<DictionaryData> exerciseEnNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE,
                        DictionaryRepository.EXERCISE_NAME, exercise.getDExerciseName(),
                        LocalDateTime.now());
        List<DictionaryData> exerciseCategoryEnNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.ENG_LANGUAGE, DictionaryRepository.EXERCISE_CATEGORY_NAME,
                        exercise.getExerciseCategory().getDExerciseCategoryName(), LocalDateTime.now());
        List<DictionaryData> exerciseNoNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE,
                        DictionaryRepository.EXERCISE_NAME, exercise.getDExerciseName(),
                        LocalDateTime.now());
        List<DictionaryData> exerciseCategoryNoNames = dictionaryRepository.
                findDictionaryValue(DictionaryRepository.NOR_LANGUAGE, DictionaryRepository.EXERCISE_CATEGORY_NAME,
                        exercise.getExerciseCategory().getDExerciseCategoryName(), LocalDateTime.now());
        return ExerciseResponseDTO.builder()
                .id(exercise.getId())
                .exerciseId(exercise.getExercise_id())
                .nameEn(exerciseEnNames.isEmpty() ? "" : exerciseEnNames.get(0).getDvalue())
                .nameNo(exerciseNoNames.isEmpty() ? "" : exerciseNoNames.get(0).getDvalue())
                .category(ExerciseResponseCategoryDTO.builder()
                        .id(exercise.getExerciseCategory().getId())
                        .nameEn(exerciseCategoryEnNames.get(0).getDvalue())
                        .nameNo(exerciseCategoryNoNames.get(0).getDvalue())
                        .build())
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
        final String dataKey = getNewDictionaryDataKey();
        createDictionaryDataKey(dataKey, exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final Exercise exercise = new Exercise();
        exercise.setDExerciseName(dataKey);
        exercise.setExercise_id(exerciseRequestDTO.getExerciseId());
        exercise.setExerciseCategory(exerciseCategoryDb);
        return exerciseToDto(exerciseRepository.save(exercise));
    }

    private String getNewDictionaryDataKey() {
        final List<DictionaryData> allExerciseEnNames = dictionaryRepository.
                findDictionaryAllValues(DictionaryRepository.ENG_LANGUAGE,
                        DictionaryRepository.EXERCISE_NAME, LocalDateTime.now());
        final String biggestKey =  allExerciseEnNames.stream().sorted((d1, d2) ->
                Integer.compare(Integer.parseInt(d2.getDkey()), Integer.parseInt(d1.getDkey())))
                    .findFirst().get().getDkey();
        return "" + (Integer.parseInt(biggestKey) + 10);
    }

    private void createDictionaryDataKey(String dKey, String exerciseNameEn, String exerciseNameNo) {
        final DictionaryData dataEn = new DictionaryData();
        dataEn.setDlanguage(DictionaryRepository.ENG_LANGUAGE);
        dataEn.setDname(DictionaryRepository.EXERCISE_NAME);
        dataEn.setDkey(dKey);
        dataEn.setDvalue(exerciseNameEn);
        dictionaryRepository.save(dataEn);
        final DictionaryData dataNo = new DictionaryData();
        dataNo.setDlanguage(DictionaryRepository.NOR_LANGUAGE);
        dataNo.setDname(DictionaryRepository.EXERCISE_NAME);
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
        createDictionaryDataKey(dataKey, exerciseRequestDTO.getNameEn(), exerciseRequestDTO.getNameNo());
        final ExerciseCategory exerciseCategoryDb = exerciseCategoryRepository
            .findOne(exerciseRequestDTO.getCategory().getId());
        if (exerciseCategoryDb == null) {
            throw new ResourceNotFoundException("Category not found in database: "
                    + exerciseRequestDTO.getCategory().getId());
        }
        existedExercise.setExerciseCategory(exerciseCategoryDb);
        existedExercise.setDExerciseName(dataKey);
        existedExercise.setExercise_id(exerciseRequestDTO.getExerciseId());
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
        final ExerciseResponseDTO exerciseResponseDTO = exerciseToDto(exercise);
        exerciseRepository.delete(id);
        return exerciseResponseDTO;
    }
}
