package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.Exercise;
import com.osomapps.pt.exercises.ExerciseBodypart;
import com.osomapps.pt.exercises.ExerciseRepository;
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
    private ExerciseBodypartRepository exerciseBodypartRepository;
    @Mock
    private ExerciseTypeRepository exerciseTypeRepository;
    @Mock
    private ExerciseInputRepository exerciseInputRepository;
    @Mock
    private ExerciseOutputRepository exerciseOutputRepository;
    @Mock
    private DictionaryService dictionaryService;

    @InjectMocks
    private AdminExerciseService tokenService;

    @Test
    public void findAll() {
        tokenService.findAll();
        verify(exerciseRepository).findAll(any(Sort.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() {
        tokenService.findOne(1L);
    }

    @Test
    public void findOne() {
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        when(exerciseRepository.findOne(eq(1L))).thenReturn(savedExercise);
        tokenService.findOne(1L);
        verify(exerciseRepository).findOne(eq(1L));
    }

    @Test
    public void create() {
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setBodypart(new ExerciseBodypartRequestDTO(1L));
        exerciseRequestDTO.setNameEn("nameEn");
        exerciseRequestDTO.setNameEn("nameNo");
        exerciseRequestDTO.setTypes(Collections.emptyList());
        exerciseRequestDTO.setInputs(Collections.emptyList());
        exerciseRequestDTO.setOutputs(Collections.emptyList());
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        when(exerciseBodypartRepository.findOne(eq(1L))).thenReturn(existedExerciseBodypart);
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);
        tokenService.create(exerciseRequestDTO);
        verify(exerciseRepository).save(any(Exercise.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        tokenService.update(1L, new ExerciseRequestDTO());
    }

    @Test
    public void update() {
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setBodypart(new ExerciseBodypartRequestDTO(1L));
        exerciseRequestDTO.setNameEn("nameEn");
        exerciseRequestDTO.setNameEn("nameNo");
        exerciseRequestDTO.setTypes(Collections.emptyList());
        exerciseRequestDTO.setInputs(Collections.emptyList());
        exerciseRequestDTO.setOutputs(Collections.emptyList());
        Exercise existedExercise = new Exercise();
        when(exerciseRepository.findOne(eq(1L))).thenReturn(existedExercise);
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        when(exerciseBodypartRepository.findOne(eq(1L))).thenReturn(existedExerciseBodypart);
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);
        tokenService.update(1L, exerciseRequestDTO);
        verify(exerciseRepository).save(any(Exercise.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        tokenService.delete(1L);
    }

    @Test
    public void delete() {
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        when(exerciseRepository.findOne(eq(1L))).thenReturn(savedExercise);
        tokenService.delete(1L);
        verify(exerciseRepository).delete(eq(1L));
    }

}
