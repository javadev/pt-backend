package com.osomapps.pt.admin.exercise;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminExerciseEquipmentTypeResourceTest {

    @Mock
    private AdminExerciseEquipmentTypeService adminExerciseEquipmentTypeService;    

    @InjectMocks
    private AdminExerciseEquipmentTypeResource adminExerciseEquipmentTypeResource;

    @Test
    public void findAll() {
        adminExerciseEquipmentTypeResource.findAll();
        verify(adminExerciseEquipmentTypeService).findAll();
    }
}
