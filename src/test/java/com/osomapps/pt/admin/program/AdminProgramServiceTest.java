package com.osomapps.pt.admin.program;

import com.osomapps.pt.ResourceNotFoundException;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemReport;
import com.osomapps.pt.programs.InWorkoutItemSetReport;
import com.osomapps.pt.programs.ParseUser;
import com.osomapps.pt.programs.ParseUserRepository;
import com.osomapps.pt.programs.ParseWorkout;
import com.osomapps.pt.programs.ParseWorkoutItem;
import com.osomapps.pt.programs.ParseWorkoutItemRepository;
import com.osomapps.pt.programs.ParseWorkoutRepository;
import com.osomapps.pt.programs.Program;
import com.osomapps.pt.programs.ProgramRepository;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramServiceTest {

    @Mock
    private ProgramRepository programRepository;
    @Mock
    private ParseUserRepository parseUserRepository;
    @Mock
    private ParseWorkoutRepository parseWorkoutRepository;
    @Mock
    private ParseWorkoutItemRepository parseWorkoutItemRepository;
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
        when(programRepository.findOne(eq(1L))).thenReturn(
                new Program().setParseUsers(
                        Arrays.asList(new ParseUser().setParseWorkouts(
                            Arrays.asList(new ParseWorkout().setParseWorkoutItems(
                                Arrays.asList(new ParseWorkoutItem())))))));
        adminProgramService.findOne(1L);
        verify(programRepository).findOne(eq(1L));
    }

    @Test
    public void create() throws IOException {
        ProgramRequestDTO programRequestDTO = new ProgramRequestDTO();
        final java.io.ByteArrayOutputStream result = new java.io.ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = AdminProgramServiceTest.class.getResourceAsStream("dataurl01.txt")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }
        programRequestDTO.setDataUrl(result.toString());
        when(parseUserRepository.save(anyList())).thenAnswer(i -> i.getArguments()[0]);
        when(parseWorkoutRepository.save(anyList())).thenAnswer(i -> i.getArguments()[0]);
        when(programRepository.save(any(Program.class))).thenAnswer(i -> i.getArguments()[0]);
        adminProgramService.create(programRequestDTO);
        verify(programRepository).save(any(Program.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void update_not_found() {
        adminProgramService.update(1L, new ProgramRequestDTO());
    }

    @Test
    public void update() throws IOException {
        ProgramRequestDTO programRequestDTO = new ProgramRequestDTO();
        final java.io.ByteArrayOutputStream result = new java.io.ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = AdminProgramServiceTest.class.getResourceAsStream("dataurl01.txt")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }
        programRequestDTO.setDataUrl(result.toString());
        when(programRepository.findOne(eq(1L))).thenReturn(
        new Program().setParseUsers(
                Arrays.asList(new ParseUser().setParseWorkouts(
                    Arrays.asList(new ParseWorkout().setParseWorkoutItems(
                        Arrays.asList(new ParseWorkoutItem())))))));
        when(parseUserRepository.save(anyList())).thenAnswer(i -> i.getArguments()[0]);
        when(parseWorkoutRepository.save(anyList())).thenAnswer(i -> i.getArguments()[0]);
        when(programRepository.save(any(Program.class))).thenAnswer(i -> i.getArguments()[0]);
        adminProgramService.update(1L, programRequestDTO);
        verify(programRepository).save(any(Program.class));
    }

    @Test(expected = ResourceNotFoundException.class)
    public void delete_not_found() {
        adminProgramService.delete(1L);
    }

    @Test
    public void delete() {
        when(programRepository.findOne(eq(1L))).thenReturn(
                new Program().setParseUsers(
                        Arrays.asList(new ParseUser().setParseWorkouts(
                            Arrays.asList(new ParseWorkout().setParseWorkoutItems(
                                Arrays.asList(new ParseWorkoutItem())))))));
        adminProgramService.delete(1L);
        verify(programRepository).delete(any(Program.class));
    }
    
    @Test
    public void createXlsx() throws IOException {
        final java.io.ByteArrayOutputStream result = new java.io.ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = AdminProgramServiceTest.class.getResourceAsStream("dataurl01.txt")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
        }
        when(inWorkoutItemRepository.findOne(eq(1L))).thenReturn(new InWorkoutItem()
            .setInWorkoutItemReports(Arrays.asList(new InWorkoutItemReport()
                .setInWorkoutItemSetReports(Arrays.asList(new InWorkoutItemSetReport()
                    .setRepetitions(1)
                    .setWeight(1))))));
        when(programRepository.findOne(eq(1L))).thenReturn(new Program()
                .setData_url(result.toString())
                .setParseUsers(Arrays.asList(new ParseUser()
                        .setSheet_index(0)
                        .setParseWorkouts(Arrays.asList(new ParseWorkout()
                            .setRow_index(5)
                            .setColumn_index(10)
                            .setParseWorkoutItems(Arrays.asList(new ParseWorkoutItem()
                                .setIn_workout_item_id(1L)
                                .setColumn_index(1)
                                .setRow_index(1))))))));
        adminProgramService.createXlsx(1L);
    }
}
