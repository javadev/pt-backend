package com.osomapps.pt.xlsx;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWorkout;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItemSet;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.util.FastByteArrayOutputStream;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class XlsxProgramModifierTest {

    private InUser getInUserWithProgram() {
        return new InUser().setInPrograms(
                Arrays.asList(new InProgram().setInWorkouts(Arrays.asList(
                new InWorkout()
                        .setWorkout_index(0)
                        .setGoal_index(0)                        
                        .setInWarmupWorkoutItems(Arrays.asList(
                                new InWarmupWorkoutItem().setTime_in_sec(60)))
                        .setInWorkoutItems(Arrays.asList(
                                new InWorkoutItem().setInWorkoutItemSets(Arrays.asList(
                                        new InWorkoutItemSet().setRepetitions(1)
                                        .setTime_in_sec(60)
                                        .setWeight(60.1F)
                                ))))
                ))));
    }

    @Test
    public void updateCellData() {
        FastByteArrayOutputStream localOutputStream = getOutputStream();

        DictionaryService dictionaryService = mock(DictionaryService.class);
        XlsxProgramModifier xlsxProgramModifier = XlsxProgramModifier.of(
                localOutputStream.getInputStream(), dictionaryService);
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title),
                    anyString(), anyString())).thenReturn("Loose weight");
        xlsxProgramModifier.updateCellData(mock(OutputStream.class), getInUserWithProgram()
                .setWeight(60F)
                .setInUserGoals(Arrays.asList(new InUserGoal(), new InUserGoal())));
    }

    @Test
    public void updateCellData_no_goals() {
        FastByteArrayOutputStream localOutputStream = getOutputStream();

        DictionaryService dictionaryService = mock(DictionaryService.class);
        XlsxProgramModifier xlsxProgramModifier = XlsxProgramModifier.of(
                localOutputStream.getInputStream(), dictionaryService);
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title),
                    anyString(), anyString())).thenReturn("Loose weight");
        xlsxProgramModifier.updateCellData(mock(OutputStream.class), getInUserWithProgram()
                .setWeight(60F)
                .setInUserGoals(Collections.emptyList()));
    }

    @Test
    public void updateCellData_one_goal() {
        FastByteArrayOutputStream localOutputStream = getOutputStream();

        DictionaryService dictionaryService = mock(DictionaryService.class);
        XlsxProgramModifier xlsxProgramModifier = XlsxProgramModifier.of(
                localOutputStream.getInputStream(), dictionaryService);
        xlsxProgramModifier.updateCellData(mock(OutputStream.class), getInUserWithProgram()
                .setWeight(60F)
                .setInUserGoals(Arrays.asList(new InUserGoal())));
    }

    private FastByteArrayOutputStream getOutputStream() {
        FastByteArrayOutputStream localOutputStream = new FastByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = XlsxProgramParser.class.getResourceAsStream("program01.xlsx")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                localOutputStream.write(buffer, 0, length);
            }
        } catch (IOException ex) {
        }
        return localOutputStream;
    }

}
