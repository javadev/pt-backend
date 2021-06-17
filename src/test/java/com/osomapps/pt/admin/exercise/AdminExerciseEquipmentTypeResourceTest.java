package com.osomapps.pt.admin.exercise;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseEquipmentTypeResourceTest {

    @Mock private AdminExerciseEquipmentTypeService adminExerciseEquipmentTypeService;

    @InjectMocks private AdminExerciseEquipmentTypeResource adminExerciseEquipmentTypeResource;

    @Test
    public void findAll() {
        adminExerciseEquipmentTypeResource.findAll();
        verify(adminExerciseEquipmentTypeService).findAll();
    }
}
