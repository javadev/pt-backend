package com.osomapps.pt.admin.exercise;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseFileResourceTest {

    @Mock private AdminExerciseFileService adminExerciseFileService;

    @InjectMocks private AdminExerciseFileResource adminExerciseFileResource;

    @Test
    public void findOne() {
        adminExerciseFileResource.findAll(Arrays.asList(1L));
        verify(adminExerciseFileService).findAll(eq(Arrays.asList(1L)));
    }
}
