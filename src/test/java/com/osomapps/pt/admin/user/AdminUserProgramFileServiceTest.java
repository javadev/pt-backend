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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.FastByteArrayOutputStream;

@ExtendWith(MockitoExtension.class)
public class AdminUserProgramFileServiceTest {

    @Mock
    private DictionaryService dictionaryService;
    @Mock
    private InUserRepository inUserRepository;
    @InjectMocks
    private AdminUserProgramFileService adminUserProgramFileService;

    @Test
    public void createXlsx_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserProgramFileService.createXlsx(1L, new FastByteArrayOutputStream());});
    }

    @Test
    public void createXlsx() {
        when(inUserRepository.findOne(eq(1L))).thenReturn(new InUser().setInPrograms(
                Arrays.asList(new InProgram().setInWorkouts(Arrays.asList(
                new InWorkout()
                        .setWorkout_index(0)
                        .setGoal_index(0)
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
