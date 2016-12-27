package com.osomapps.pt.admin.program;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import com.osomapps.pt.programs.InWorkoutItemSetReport;
import com.osomapps.pt.programs.ParseGoal;
import com.osomapps.pt.programs.ParseUserGroup;
import com.osomapps.pt.programs.ParseWorkout;
import com.osomapps.pt.programs.ParseWorkoutItem;
import com.osomapps.pt.programs.ParseWorkoutItemRepository;
import com.osomapps.pt.programs.ParseWorkoutRepository;
import com.osomapps.pt.programs.ParseProgram;
import com.osomapps.pt.programs.ProgramRepository;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;
import com.osomapps.pt.programs.ParseGoalRepository;
import com.osomapps.pt.programs.ParsePart;
import com.osomapps.pt.programs.ParsePartRepository;
import com.osomapps.pt.programs.ParseRound;
import com.osomapps.pt.programs.ParseRoundRepository;
import com.osomapps.pt.programs.ParseUserGroupRepository;
import com.osomapps.pt.programs.ParseWarmupWorkoutItemRepository;
import com.osomapps.pt.programs.ParseWorkoutItemSet;
import com.osomapps.pt.programs.ParseWorkoutItemSetRepository;
import static org.mockito.Matchers.anyList;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramServiceTest {

    @Mock
    private ProgramRepository programRepository;
    @Mock
    private ParseGoalRepository parseGoalRepository;
    @Mock
    private ParseUserGroupRepository parseUserGroupRepository;
    @Mock
    private ParseRoundRepository parseRoundRepository;
    @Mock
    private ParsePartRepository parsePartRepository;
    @Mock
    private ParseWorkoutRepository parseWorkoutRepository;
    @Mock
    private ParseWarmupWorkoutItemRepository parseWarmupWorkoutItemRepository;
    @Mock
    private ParseWorkoutItemRepository parseWorkoutItemRepository;
    @Mock
    private ParseWorkoutItemSetRepository parseWorkoutItemSetRepository;
    @Mock
    private InWorkoutItemRepository inWorkoutItemRepository;
    @Mock
    private AdminProgramAssignService adminProgramAssignService;

    @InjectMocks
    private AdminProgramService adminProgramService;

    @Test
    public void findAll() {
        adminProgramService.findAll();
        verify(programRepository).findAll(any(Sort.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne_not_found() {
        adminProgramService.findOne(1L);
    }

    @Test
    public void findOne() {
        when(programRepository.findOne(eq(1L))).thenReturn(new ParseProgram().setParseGoals(
                Arrays.asList(new ParseGoal().setParseUserGroups(
                        Arrays.asList(new ParseUserGroup().setParseRounds(
                            Arrays.asList(new ParseRound().setParseParts(
                                Arrays.asList(new ParsePart().setParseWorkouts(
                                        Arrays.asList(
                                            new ParseWorkout().setParseWorkoutItems(
                                                Arrays.asList(new ParseWorkoutItem()
                                                        .setParseWorkoutItemSets(Arrays.asList(new ParseWorkoutItemSet())))))))))))))));
        adminProgramService.findOne(1L);
        verify(programRepository).findOne(eq(1L));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void create() throws IOException {
        ProgramRequestDTO programRequestDTO = new ProgramRequestDTO();
        final java.io.ByteArrayOutputStream result = new java.io.ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = AdminProgramServiceTest.class.getResourceAsStream("dataurl02.txt")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }
        programRequestDTO.setDataUrl(result.toString());
        when(parseGoalRepository.save(any(Iterable.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseWorkoutRepository.save(any(Iterable.class))).thenAnswer(i -> i.getArguments()[0]);
        when(programRepository.save(any(ParseProgram.class))).thenAnswer(i -> i.getArguments()[0]);
        when(adminProgramAssignService.assign(anyList())).thenAnswer(i -> i.getArguments()[0]);
        adminProgramService.create(programRequestDTO);
        verify(programRepository).save(any(ParseProgram.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminProgramService.update(1L, new ProgramRequestDTO());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void update() throws IOException {
        ProgramRequestDTO programRequestDTO = new ProgramRequestDTO();
        final java.io.ByteArrayOutputStream result = new java.io.ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = AdminProgramServiceTest.class.getResourceAsStream("dataurl02.txt")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }
        programRequestDTO.setDataUrl(result.toString());
        when(programRepository.findOne(eq(1L))).thenReturn(new ParseProgram().setParseGoals(
                Arrays.asList(new ParseGoal().setParseUserGroups(
                        Arrays.asList(new ParseUserGroup().setParseRounds(
                            Arrays.asList(new ParseRound().setParseParts(
                                Arrays.asList(new ParsePart().setParseWorkouts(
                                        Arrays.asList(
                                            new ParseWorkout().setParseWorkoutItems(
                                                Arrays.asList(new ParseWorkoutItem())))))))))))));
        when(parseGoalRepository.save(any(Iterable.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseWorkoutRepository.save(any(Iterable.class))).thenAnswer(i -> i.getArguments()[0]);
        when(programRepository.save(any(ParseProgram.class))).thenAnswer(i -> i.getArguments()[0]);
        when(adminProgramAssignService.assign(anyList())).thenAnswer(i -> i.getArguments()[0]);
        adminProgramService.update(1L, programRequestDTO);
        verify(programRepository).save(any(ParseProgram.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminProgramService.delete(1L);
    }

    @Test
    public void delete() {
        when(programRepository.findOne(eq(1L))).thenReturn(new ParseProgram().setParseGoals(
                Arrays.asList(new ParseGoal().setParseUserGroups(
                        Arrays.asList(new ParseUserGroup().setParseRounds(
                            Arrays.asList(new ParseRound().setParseParts(
                                Arrays.asList(new ParsePart().setParseWorkouts(
                                        Arrays.asList(
                                            new ParseWorkout().setParseWorkoutItems(
                                                Arrays.asList(new ParseWorkoutItem()
                                                .setParseWorkoutItemSets(Arrays.asList(new ParseWorkoutItemSet())))))))))))))));
        adminProgramService.delete(1L);
        verify(programRepository).delete(any(ParseProgram.class));
    }
    
    @Test
    public void createXlsx() throws IOException {
        final java.io.ByteArrayOutputStream result = new java.io.ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = AdminProgramServiceTest.class.getResourceAsStream("dataurl02.txt")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }
        when(inWorkoutItemRepository.findOne(eq(1L))).thenReturn(new InWorkoutItem()
            .setInWorkoutItemReports(Arrays.asList(new InWorkoutItemReport()
                .setInWorkoutItemSetReports(Arrays.asList(new InWorkoutItemSetReport()
                    .setRepetitions(1)
                    .setWeight(1F))))));
        when(programRepository.findOne(eq(1L))).thenReturn(new ParseProgram()
                .setData_url(result.toString())
                .setParseGoals(Arrays.asList(new ParseGoal()
                        .setSheet_index(0)
                        .setParseUserGroups(Arrays.asList(new ParseUserGroup()
                                .setParseRounds(Arrays.asList(new ParseRound()
                                        .setParseParts(Arrays.asList(new ParsePart()
                                                .setParseWorkouts(Arrays.asList(new ParseWorkout()
                            .setRow_index(5)
                            .setColumn_index(10)
                            .setParseWorkoutItems(Arrays.asList(new ParseWorkoutItem()
                                .setParseWorkoutItemSets(Arrays.asList(new ParseWorkoutItemSet()))
                                .setIn_workout_item_id(1L)
                                .setColumn_index(1)
                                .setRow_index(1))))))))))))));
        adminProgramService.createXlsx(1L, new java.io.ByteArrayOutputStream());
    }
}
