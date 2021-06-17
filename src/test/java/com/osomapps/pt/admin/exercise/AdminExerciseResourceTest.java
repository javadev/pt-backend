package com.osomapps.pt.admin.exercise;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseResourceTest {

    @Mock private AdminExerciseService adminExerciseService;

    @InjectMocks private AdminExerciseResource adminExerciseResource;

    @Test
    public void findAll() {
        adminExerciseResource.findAll();
        verify(adminExerciseService).findAll();
    }

    @Test
    public void findOne() {
        adminExerciseResource.findOne(1L);
        verify(adminExerciseService).findOne(eq(1L));
    }

    @Test
    public void create() {
        adminExerciseResource.create(new ExerciseRequestDTO());
        verify(adminExerciseService).create(any(ExerciseRequestDTO.class));
    }

    @Test
    public void update() {
        adminExerciseResource.update(1L, new ExerciseRequestDTO());
        verify(adminExerciseService).update(eq(1L), any(ExerciseRequestDTO.class));
    }

    @Test
    public void delete() {
        adminExerciseResource.delete(1L);
        verify(adminExerciseService).delete(anyLong());
    }
}
