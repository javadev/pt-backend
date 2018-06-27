package com.osomapps.pt.admin.user;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import com.osomapps.pt.programs.InWorkoutItemSet;
import com.osomapps.pt.programs.InWorkoutItemSetReport;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminUserProgramServiceTest {

    @Mock
    private InProgramRepository inProgramRepository;

    @InjectMocks
    private AdminUserProgramService adminUserProgramService;

    private InProgram getInProgram() {
        return new InProgram()
        .setInWorkouts(Arrays.asList(
                new InWorkout()
                    .setInWarmupWorkoutItems(Arrays.asList(new InWarmupWorkoutItem()
                    ))
                    .setInWorkoutItems(Arrays.asList(
                            new InWorkoutItem()
                    .setInWorkoutItemSets(Arrays.asList(new InWorkoutItemSet()))
                    .setInWorkoutItemReports(Arrays.asList(
                            new InWorkoutItemReport()
                                    .setInWorkoutItemSetReports(Arrays.asList(
                                            new InWorkoutItemSetReport()))
                    ))))
        ));
    }

    @Test
    public void findAll() {
        when(inProgramRepository.findAll()).thenReturn(Arrays.asList(getInProgram()));
        List<UserProgramResponseDTO> responseDTOs = adminUserProgramService.findAll();
        assertThat(responseDTOs.size(), equalTo(1));
    }

    @Test
    public void findOne_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserProgramService.findOne(1L);});
    }

    @Test
    public void findOne() {
        when(inProgramRepository.findOne(eq(1L))).thenReturn(getInProgram());
        UserProgramResponseDTO responseDTO = adminUserProgramService.findOne(1L);
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void create() {
        when(inProgramRepository.save(any(InProgram.class))).thenAnswer(i -> i.getArguments()[0]);
        UserProgramResponseDTO responseDTO = adminUserProgramService.create(
                new UserProgramRequestDTO().setName("name")
                        .setWorkouts(Arrays.asList(
                                new UserWorkoutRequestDTO()
                                .setItems(Arrays.asList(
                                        new UserWorkoutItemRequestDTO()
                                .setSets(Arrays.asList(
                                new UserWorkoutItemSetRequestDTO())))))
                        ));
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void update_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserProgramService.update(1L, new UserProgramRequestDTO());});
    }

    @Test
    public void update() {
        when(inProgramRepository.findOne(eq(1L))).thenReturn(getInProgram());
        when(inProgramRepository.save(any(InProgram.class))).thenAnswer(i -> i.getArguments()[0]);
        UserProgramResponseDTO responseDTO = adminUserProgramService.update(1L,
                new UserProgramRequestDTO().setName("name")
                        .setWorkouts(Arrays.asList(
                                new UserWorkoutRequestDTO()
                                .setItems(Arrays.asList(new UserWorkoutItemRequestDTO()))
                        )));
        assertThat(responseDTO, notNullValue());
    }

    @Test
    public void delete_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminUserProgramService.delete(1L);});
    }

    @Test
    public void delete() {
        when(inProgramRepository.findOne(eq(1L))).thenReturn(getInProgram());
        UserProgramResponseDTO responseDTO = adminUserProgramService.delete(1L);
        assertThat(responseDTO, notNullValue());
    }

}
