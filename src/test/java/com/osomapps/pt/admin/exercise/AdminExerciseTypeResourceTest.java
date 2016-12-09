package com.osomapps.pt.admin.exercise;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.runner.RunWith;
import static org.mockito.Mockito.verify;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseTypeResourceTest {

    @Mock
    private AdminExerciseTypeService adminExerciseTypeService;    

    @InjectMocks
    private AdminExerciseTypeResource adminExerciseTypeResource;

    @Test
    public void findAll() {
        adminExerciseTypeResource.findAll();
        verify(adminExerciseTypeService).findAll();
    }
}
