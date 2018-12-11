package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.UnauthorizedException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.Exercise;
import com.osomapps.pt.exercises.ExerciseBodypart;
import com.osomapps.pt.exercises.ExerciseFile;
import com.osomapps.pt.exercises.ExerciseFilePreview;
import com.osomapps.pt.exercises.ExerciseFilePreviewRepository;
import com.osomapps.pt.exercises.ExerciseFileRepository;
import com.osomapps.pt.exercises.ExerciseRepository;
import com.osomapps.pt.tokenemail.DataurlValidator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.data.domain.Sort;
import org.springframework.validation.Errors;

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
    private ExerciseFileRepository exerciseFileRepository;
    @Mock
    private ExerciseFilePreviewRepository exerciseFilePreviewRepository;
    @Mock
    private DictionaryService dictionaryService;
    @Mock
    private DataurlValidator dataurlValidator;

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
        when(exerciseRepository.findById(eq(1L))).thenReturn(Optional.of(savedExercise));
        tokenService.findOne(1L);
        verify(exerciseRepository).findById(eq(1L));
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
        exerciseRequestDTO.setFiles(Arrays.asList(new ExerciseFileRequestDTO().setData_url(
                "data:image/gif;base64,"
                + "R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0jvb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///"
                + "yH5BAAAAAAALAAAAAAQAA4AAARe8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjg"
                + "aORCMxIC6e0CcguWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7")));
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        when(exerciseBodypartRepository.findById(eq(1L))).thenReturn(Optional.of(existedExerciseBodypart));
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        savedExercise.setExerciseFiles(Arrays.asList(new ExerciseFilePreview()));
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);
        tokenService.create(exerciseRequestDTO);
        verify(exerciseRepository).save(any(Exercise.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void create_invalid_data_url() {
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setBodypart(new ExerciseBodypartRequestDTO(1L));
        exerciseRequestDTO.setNameEn("nameEn");
        exerciseRequestDTO.setNameEn("nameNo");
        exerciseRequestDTO.setTypes(Collections.emptyList());
        exerciseRequestDTO.setInputs(Collections.emptyList());
        exerciseRequestDTO.setOutputs(Collections.emptyList());
        exerciseRequestDTO.setFiles(Arrays.asList(new ExerciseFileRequestDTO()));
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        when(exerciseBodypartRepository.findById(eq(1L))).thenReturn(Optional.of(existedExerciseBodypart));
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        savedExercise.setExerciseFiles(Arrays.asList(new ExerciseFilePreview()));
        tokenService.create(exerciseRequestDTO);
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
        exerciseRequestDTO.setFiles(Arrays.asList(new ExerciseFileRequestDTO().setData_url(
                "data:image/gif;base64,"
                + "R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0jvb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///"
                + "yH5BAAAAAAALAAAAAAQAA4AAARe8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjg"
                + "aORCMxIC6e0CcguWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7")));
        Exercise existedExercise = new Exercise();
        when(exerciseRepository.findById(eq(1L))).thenReturn(Optional.of(existedExercise));
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        when(exerciseBodypartRepository.findById(eq(1L))).thenReturn(Optional.of(existedExerciseBodypart));
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        savedExercise.setExerciseFiles(Arrays.asList(new ExerciseFilePreview()));
        when(exerciseRepository.save(any(Exercise.class))).thenReturn(savedExercise);
        tokenService.update(1L, exerciseRequestDTO);
        verify(exerciseRepository).save(any(Exercise.class));
    }

    @Test(expected = UnauthorizedException.class)
    public void update_invalid_data_url() {
        ExerciseRequestDTO exerciseRequestDTO = new ExerciseRequestDTO();
        exerciseRequestDTO.setBodypart(new ExerciseBodypartRequestDTO(1L));
        exerciseRequestDTO.setNameEn("nameEn");
        exerciseRequestDTO.setNameEn("nameNo");
        exerciseRequestDTO.setTypes(Collections.emptyList());
        exerciseRequestDTO.setInputs(Collections.emptyList());
        exerciseRequestDTO.setOutputs(Collections.emptyList());
        exerciseRequestDTO.setFiles(Arrays.asList(new ExerciseFileRequestDTO()));
        Exercise existedExercise = new Exercise();
        when(exerciseRepository.findById(eq(1L))).thenReturn(Optional.of(existedExercise));
        ExerciseBodypart existedExerciseBodypart = new ExerciseBodypart();
        existedExerciseBodypart.setId(1L);
        existedExerciseBodypart.setDExerciseBodypartName("10");
        when(exerciseBodypartRepository.findById(eq(1L))).thenReturn(Optional.of(existedExerciseBodypart));
        Exercise savedExercise = new Exercise();
        savedExercise.setId(1L);
        savedExercise.setDExerciseName("10");
        savedExercise.setExerciseBodypart(existedExerciseBodypart);
        savedExercise.setExerciseFiles(Arrays.asList(new ExerciseFilePreview()));
        tokenService.update(1L, exerciseRequestDTO);
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
        when(exerciseRepository.findById(eq(1L))).thenReturn(Optional.of(savedExercise));
        tokenService.delete(1L);
        verify(exerciseRepository).deleteById(eq(1L));
    }

}
