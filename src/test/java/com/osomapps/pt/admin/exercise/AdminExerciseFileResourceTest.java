package com.osomapps.pt.admin.exercise;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdminExerciseFileResourceTest {

    @Mock
    private AdminExerciseFileService adminExerciseFileService;    

    @InjectMocks
    private AdminExerciseFileResource adminExerciseFileResource;

    @Test
    public void findOne() {
        adminExerciseFileResource.findAll(Arrays.asList(1L));
        verify(adminExerciseFileService).findAll(eq(Arrays.asList(1L)));
    }
}
