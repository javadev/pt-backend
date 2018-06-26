package com.osomapps.pt.admin.program;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.ParseExercise;
import com.osomapps.pt.programs.ParseExerciseRepository;
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
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
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
import com.osomapps.pt.token.InUser;
import java.io.OutputStream;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AdminProgramServiceTest {

    @Mock
    private ProgramRepository programRepository;
    @Mock
    private ParseExerciseRepository parseExerciseRepository;
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

    @Test
    public void findOne_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminProgramService.findOne(1L);});
    }

    @Test
    public void findOne() {
        when(programRepository.findOne(eq(1L))).thenReturn(new ParseProgram()
                .setParseExercises(Arrays.asList(new ParseExercise()))
                .setParseGoals(
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
        when(adminProgramAssignService.assign(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        adminProgramService.create(programRequestDTO);
        verify(programRepository).save(any(ParseProgram.class));
    }

    @Test
    public void update_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminProgramService.update(1L, new ProgramRequestDTO());});
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
        when(adminProgramAssignService.assign(any(InUser.class))).thenAnswer(i -> i.getArguments()[0]);
        adminProgramService.update(1L, programRequestDTO);
        verify(programRepository).save(any(ParseProgram.class));
    }

    @Test
    public void delete_not_found() {
        assertThrows(ResourceNotFoundException.class, () -> {adminProgramService.delete(1L);});
    }

    @Test
    public void delete() {
        when(programRepository.findOne(eq(1L))).thenReturn(new ParseProgram()
                .setParseExercises(Arrays.asList(new ParseExercise()))
                .setParseGoals(
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
    public void dataUrlToOutputStream() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        adminProgramService.dataUrlToOutputStream("data:image/gif;base64,R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0j"
                + "vb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///yH5BAAAAAAALAAAAAAQAA4AAA"
                + "Re8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjgaORCMxIC6e0Cc"
                + "guWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7", outputStream);
        verify(outputStream).write(anyObject());
    }

    @Test
    public void dataUrlToOutputStream_wrong_data() throws IOException {
        OutputStream outputStream = mock(OutputStream.class);
        adminProgramService.dataUrlToOutputStream("data:image/gif;base64,!!!", outputStream);
        verify(outputStream, never()).write(anyObject());
    }

}
