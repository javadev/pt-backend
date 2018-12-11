package com.osomapps.pt.admin.exercise;

import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.exercises.ExerciseEquipmentType;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseEquipmentTypeServiceTest {

    @Mock
    private ExerciseEquipmentTypeRepository exerciseEquipmentTypeRepository;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private AdminExerciseEquipmentTypeService adminExerciseEquipmentTypeService;

    @Test
    public void findAll() {
        when(exerciseEquipmentTypeRepository.findAll()).thenReturn(Arrays.asList(new ExerciseEquipmentType()));
        List<ExerciseEquipmentTypeResponseDTO> responseDTOs = adminExerciseEquipmentTypeService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

}
