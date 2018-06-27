package com.osomapps.pt.admin.exercise;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdminExerciseBodypartResourceTest {

    @Mock
    private AdminExerciseBodypartService adminExerciseBodypartService;    

    @InjectMocks
    private AdminExerciseBodypartResource adminExerciseBodypartResource;

    @Test
    public void findAll() {
        adminExerciseBodypartResource.findAll();
        verify(adminExerciseBodypartService).findAll();
    }
}
