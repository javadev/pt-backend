package com.osomapps.pt.admin.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemSet;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.FastByteArrayOutputStream;

@RunWith(MockitoJUnitRunner.class)
public class AdminUserProgramFileServiceTest {

    @Mock
    private DictionaryService dictionaryService;
    @Mock
    private InUserRepository inUserRepository;
    @InjectMocks
    private AdminUserProgramFileService adminUserProgramFileService;

    @Test(expected = ResourceNotFoundException.class)
    public void createXlsx_not_found() {
        adminUserProgramFileService.createXlsx(1L, new FastByteArrayOutputStream());
    }

    @Test
    public void createXlsx() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(new InUser().setInPrograms(
                Arrays.asList(new InProgram().setInWorkouts(Arrays.asList(
                new InWorkout()
                        .setWorkout_index(0)
                        .setInWarmupWorkoutItems(Arrays.asList(
                                new InWarmupWorkoutItem().setTime_in_sec(60)))
                        .setInWorkoutItems(Arrays.asList(
                                new InWorkoutItem().setInWorkoutItemSets(Arrays.asList(
                                        new InWorkoutItemSet()
                                ))))
                )))));
        ProgramResponseDTO programResponseDTO = adminUserProgramFileService.createXlsx(
                1L, new FastByteArrayOutputStream());
        assertThat(programResponseDTO, notNullValue());
    }
}
