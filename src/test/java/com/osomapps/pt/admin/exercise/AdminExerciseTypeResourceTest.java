package com.osomapps.pt.admin.exercise;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseTypeResourceTest {

    @Mock private AdminExerciseTypeService adminExerciseTypeService;

    @InjectMocks private AdminExerciseTypeResource adminExerciseTypeResource;

    @Test
    public void findAll() {
        adminExerciseTypeResource.findAll();
        verify(adminExerciseTypeService).findAll();
    }
}
