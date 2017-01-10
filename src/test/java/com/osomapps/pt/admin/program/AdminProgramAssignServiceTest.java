package com.osomapps.pt.admin.program;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InProgramRepository;
import com.osomapps.pt.programs.InWarmupWorkoutItemRepository;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutRepository;
import com.osomapps.pt.programs.ParseGoal;
import com.osomapps.pt.programs.ParsePart;
import com.osomapps.pt.programs.ParseProgram;
import com.osomapps.pt.reportworkout.InWorkoutItemRepository;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserFacebook;
import com.osomapps.pt.token.InUserRepository;
import java.util.Arrays;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import com.osomapps.pt.programs.ParseProgramRepository;
import com.osomapps.pt.programs.ParseRound;
import com.osomapps.pt.programs.ParseUserGroup;
import com.osomapps.pt.programs.ParseWarmupWorkoutItem;
import com.osomapps.pt.programs.ParseWorkout;
import com.osomapps.pt.programs.ParseWorkoutItem;
import com.osomapps.pt.programs.ParseWorkoutItemSet;
import com.osomapps.pt.reportworkout.InWorkoutItemSetRepository;
import com.osomapps.pt.token.InUserGoal;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.springframework.data.domain.Sort;

@RunWith(MockitoJUnitRunner.class)
public class AdminProgramAssignServiceTest {

    @Mock
    private InUserRepository inUserRepository;
    @Mock
    private InProgramRepository inProgramRepository;
    @Mock
    private InWorkoutRepository inWorkoutRepository;
    @Mock
    private InWorkoutItemRepository inWorkoutItemRepository;
    @Mock
    private InWorkoutItemSetRepository inWorkoutItemSetRepository;
    @Mock
    private ParseProgramRepository parseProgramRepository;
    @Mock
    private InWarmupWorkoutItemRepository inWarmupWorkoutItemRepository;
    @Mock
    private AdminProgramScanExerciseService adminProgramScanExerciseService;
    @Mock
    private DictionaryService dictionaryService;
    @InjectMocks
    private AdminProgramAssignService adminProgramAssignService;

    @Test
    public void assign_user_group_1_two_goals() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("2")
                .setD_sex("male").setInUserGoals(Arrays.asList(new InUserGoal(), new InUserGoal())));
        verify(inProgramRepository).save(any(InProgram.class));
    }

    @Test
    public void assign_empty_program() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Collections.emptyList());
        adminProgramAssignService.assign(new InUser().setD_level("3")
                .setD_sex("male2").setInUserGoals(Arrays.asList(new InUserGoal())));
        verify(inProgramRepository, never()).save(any(InProgram.class));
    }

    @Test
    public void assign_user_unknown() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("3")
                .setD_sex("male2").setInUserGoals(Arrays.asList(new InUserGoal())));
        verify(inProgramRepository, never()).save(any(InProgram.class));
    }

    @Test
    public void assign_user_group_1() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("2")
                .setD_sex("male").setInUserGoals(Arrays.asList(new InUserGoal())));
        verify(inProgramRepository).save(any(InProgram.class));
    }

    @Test
    public void assign_empty_goals() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("2")
                .setD_sex("male").setInUserGoals(Collections.emptyList()));
        verify(inProgramRepository, never()).save(any(InProgram.class));
    }

    @Test
    public void assign_user_group_2() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("1")
                .setD_sex("male").setInUserGoals(Arrays.asList(new InUserGoal())));
        verify(inProgramRepository).save(any(InProgram.class));
    }

    @Test
    public void assign_user_group_3() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("2")
                .setD_sex("female").setInUserGoals(Arrays.asList(new InUserGoal())));
        verify(inProgramRepository).save(any(InProgram.class));
    }

    @Test
    public void assign_user_group_4() {
        when(inUserRepository.findAll()).thenReturn(Arrays.asList(
                new InUser().setInUserFacebooks(Arrays.asList(
                        new InUserFacebook().setUser_name("user_name")))));
        when(adminProgramScanExerciseService.getExerciseIdByName(anyString())).thenReturn(Optional.empty());
        when(inWorkoutItemRepository.save(any(InWorkoutItem.class))).thenAnswer(i -> i.getArguments()[0]);
        when(parseProgramRepository.findAll(any(Sort.class))).thenReturn(Arrays.asList(getParseProgram()));
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title), anyString(), anyString())).thenReturn("Loose weight");
        adminProgramAssignService.assign(new InUser().setD_level("1")
                .setD_sex("female").setInUserGoals(Arrays.asList(new InUserGoal())));
        verify(inProgramRepository).save(any(InProgram.class));
    }

    private static ParseProgram getParseProgram() {
        return new ParseProgram().setParseGoals(Arrays.asList(
                new ParseGoal()
                        .setName("Loose weight")
                        .setParseUserGroups(Arrays.asList(
                                new ParseUserGroup()
                                        .setName("1")
                                        .setParseRounds(Arrays.asList(
                                                new ParseRound().setParseParts(Arrays.asList(
                                                        new ParsePart().setParseWorkouts(Arrays.asList(
                                                                new ParseWorkout()
                                                                        .setParseWarmupWorkoutItems(Arrays.asList(
                                                                                new ParseWarmupWorkoutItem()))
                                                                        .setParseWorkoutItems(Arrays.asList(
                                                                                new ParseWorkoutItem().setParseWorkoutItemSets(Arrays.asList(
                                                                                        new ParseWorkoutItemSet()))))
                                                        )))))))))
        );
    }

    @Test
    public void multiplyLists_one_element() {
        List<ParseRound> parseRounds1 = Arrays.asList(new ParseRound().setName("1_1"));
        List<ParseRound> result = adminProgramAssignService.multiplyLists(parseRounds1, 1);
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getName(), equalTo("1_1"));
    }

    @Test
    public void multiplyLists_one_element_loops_2() {
        List<ParseRound> parseRounds1 = Arrays.asList(new ParseRound().setName("1_1"));
        List<ParseRound> result = adminProgramAssignService.multiplyLists(parseRounds1, 2);
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo("1_1"));
    }

    @Test
    public void multiplyLists_two_elements() {
        List<ParseRound> parseRounds1 = Arrays.asList(new ParseRound().setName("1_1"),
                new ParseRound().setName("1_2"));
        List<ParseRound> result = adminProgramAssignService.multiplyLists(parseRounds1, 1);
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo("1_1"));
        assertThat(result.get(1).getName(), equalTo("1_2"));
    }

    @Test
    public void multiplyLists_two_elements_loops_2() {
        List<ParseRound> parseRounds1 = Arrays.asList(new ParseRound().setName("1_1"),
                new ParseRound().setName("1_2"));
        List<ParseRound> result = adminProgramAssignService.multiplyLists(parseRounds1, 2);
        assertThat(result.size(), equalTo(4));
        assertThat(result.get(0).getName(), equalTo("1_1"));
        assertThat(result.get(1).getName(), equalTo("1_1"));
        assertThat(result.get(2).getName(), equalTo("1_2"));
        assertThat(result.get(3).getName(), equalTo("1_2"));
    }

    @Test
    public void mergeLists_one_element() {
        List<ParseWorkout> parseWorkouts1 = Arrays.asList(new ParseWorkout().setName("1_1"));
        List<ParseWorkout> result = adminProgramAssignService.mergeLists(parseWorkouts1, Collections.emptyList());
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getName(), equalTo("1_1"));
    }

    @Test
    public void mergeLists_one_element2() {
        List<ParseWorkout> parseWorkouts2 = Arrays.asList(new ParseWorkout().setName("2_1"));
        List<ParseWorkout> result = adminProgramAssignService.mergeLists(Collections.emptyList(), parseWorkouts2);
        assertThat(result.size(), equalTo(1));
        assertThat(result.get(0).getName(), equalTo("2_1"));
    }

    @Test
    public void mergeLists_two_elements() {
        List<ParseWorkout> parseWorkouts1 = Arrays.asList(new ParseWorkout().setName("1_1"));
        List<ParseWorkout> parseWorkouts2 = Arrays.asList(new ParseWorkout().setName("2_1"));
        List<ParseWorkout> result = adminProgramAssignService.mergeLists(parseWorkouts1, parseWorkouts2);
        assertThat(result.size(), equalTo(2));
        assertThat(result.get(0).getName(), equalTo("1_1"));
        assertThat(result.get(1).getName(), equalTo("2_1"));
    }

    @Test
    public void mergeLists_five_elements() {
        List<ParseWorkout> parseWorkouts1 = Arrays.asList(new ParseWorkout().setName("1_1"),
                new ParseWorkout().setName("1_2"));
        List<ParseWorkout> parseWorkouts2 = Arrays.asList(new ParseWorkout().setName("2_1"),
                new ParseWorkout().setName("2_2"),
                new ParseWorkout().setName("2_3"));
        List<ParseWorkout> result = adminProgramAssignService.mergeLists(parseWorkouts1, parseWorkouts2);
        assertThat(result.size(), equalTo(6));
        assertThat(result.get(0).getName(), equalTo("1_1"));
        assertThat(result.get(1).getName(), equalTo("2_1"));
        assertThat(result.get(2).getName(), equalTo("1_2"));
        assertThat(result.get(3).getName(), equalTo("2_2"));
        assertThat(result.get(4).getName(), equalTo("1_1"));
        assertThat(result.get(5).getName(), equalTo("2_3"));
    }

    @Test
    public void mergeLists_five_elements2() {
        List<ParseWorkout> parseWorkouts1 = Arrays.asList(new ParseWorkout().setName("1_1"),
                new ParseWorkout().setName("1_2"),
                new ParseWorkout().setName("1_3"));
        List<ParseWorkout> parseWorkouts2 = Arrays.asList(new ParseWorkout().setName("2_1"),
                new ParseWorkout().setName("2_2"));
        List<ParseWorkout> result = adminProgramAssignService.mergeLists(parseWorkouts1, parseWorkouts2);
        assertThat(result.size(), equalTo(6));
        assertThat(result.get(0).getName(), equalTo("1_1"));
        assertThat(result.get(1).getName(), equalTo("2_1"));
        assertThat(result.get(2).getName(), equalTo("1_2"));
        assertThat(result.get(3).getName(), equalTo("2_2"));
        assertThat(result.get(4).getName(), equalTo("1_3"));
        assertThat(result.get(5).getName(), equalTo("2_1"));
    }

}
