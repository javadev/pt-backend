package com.github.pt.admin.exercise;

import com.github.pt.dictionary.DictionaryService;
import com.github.pt.exercises.Exercise;
import com.github.pt.exercises.ExerciseCategory;
import com.github.pt.exercises.ExerciseRepository;
import java.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseServiceTest {
    
    @Mock
    private ExerciseRepository exerciseRepository;
    @Mock
    private ExerciseCategoryRepository exerciseCategoryRepository;
    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;
    @Mock
    private DictionaryService dictionaryService;

    @InjectMocks
    private AdminExerciseService tokenService;

    @Test
    public void findAll() {
        tokenService.findAll();
        verify(exerciseRepository).findAll(any(Sort.class));   
    }

    @Test
    public void findOne() {
        ExerciseCategory existedExerciseCategory = new ExerciseCategory();
        existedExerciseCategory.setId(1L);
        existedExerciseCategory.setDExerciseCategoryName("10"); 
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseCategory(existedExerciseCategory);
        when(exerciseRepository.findOne(eq(1L))).thenReturn(savedExercise);
        tokenService.findOne(1L);
        verify(exerciseRepository).findOne(eq(1L));   
    }

    @Test
    public void create() {
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setCategory(new ExerciseCategoryRequestDTO(1L));
        exerciseRequestDTO.setNameEn("nameEn");
        exerciseRequestDTO.setNameEn("nameNo");
        exerciseRequestDTO.setTypes(Collections.emptyList());
        ExerciseCategory existedExerciseCategory = new ExerciseCategory();
        existedExerciseCategory.setId(1L);
        existedExerciseCategory.setDExerciseCategoryName("10");
        when(exerciseCategoryRepository.findOne(eq(1L))).thenReturn(existedExerciseCategory);
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseCategory(existedExerciseCategory);
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);
        tokenService.create(exerciseRequestDTO);
        verify(exerciseRepository).save(any(Exercise.class));
    }

    @Test
    public void update() {
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setCategory(new ExerciseCategoryRequestDTO(1L));
        exerciseRequestDTO.setNameEn("nameEn");
        exerciseRequestDTO.setNameEn("nameNo");
        exerciseRequestDTO.setTypes(Collections.emptyList());
        Exercise existedExercise = new Exercise();
        when(exerciseRepository.findOne(eq(1L))).thenReturn(existedExercise);
        ExerciseCategory existedExerciseCategory = new ExerciseCategory();
        existedExerciseCategory.setId(1L);
        existedExerciseCategory.setDExerciseCategoryName("10");
        when(exerciseCategoryRepository.findOne(eq(1L))).thenReturn(existedExerciseCategory);
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseCategory(existedExerciseCategory);
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);
        tokenService.update(1L, exerciseRequestDTO);
        verify(exerciseRepository).save(any(Exercise.class));
    }

    @Test
    public void delete() {
        ExerciseCategory existedExerciseCategory = new ExerciseCategory();
        existedExerciseCategory.setId(1L);
        existedExerciseCategory.setDExerciseCategoryName("10");
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseCategory(existedExerciseCategory);
        when(exerciseRepository.findOne(eq(1L))).thenReturn(savedExercise);
        tokenService.delete(1L);
        verify(exerciseRepository).delete(eq(1L));
    }

}
