package com.github.pt.admin.exercise;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseEquipmentTypeResourceTest {

    @Mock
    private AdminExerciseBodypartService adminExerciseBodypartService;    

    @InjectMocks
    private AdminExerciseEquipmentTypeResource adminExerciseEquipmentTypeResource;

    @Test
    public void findAll() {
        adminExerciseEquipmentTypeResource.findAll();
        verify(adminExerciseBodypartService).findAll();
    }
}
