package com.osomapps.pt.admin.exercise;

import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseBodypartResourceTest {

    @Mock private AdminExerciseBodypartService adminExerciseBodypartService;

    @InjectMocks private AdminExerciseBodypartResource adminExerciseBodypartResource;

    @Test
    public void findAll() {
        adminExerciseBodypartResource.findAll();
        verify(adminExerciseBodypartService).findAll();
    }
}
