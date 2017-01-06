package com.osomapps.pt.xlsx;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.util.FastByteArrayOutputStream;

@RunWith(MockitoJUnitRunner.class)
public class XlsxProgramModifierTest {

    @Test
    public void updateCellData() {
        FastByteArrayOutputStream localOutputStream = new FastByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        try (InputStream inputStream = XlsxProgramParser.class.getResourceAsStream("program01.xlsx")) {
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                localOutputStream.write(buffer, 0, length);
            }
        } catch (IOException ex) {
        }

        DictionaryService dictionaryService = mock(DictionaryService.class);
        XlsxProgramModifier xlsxProgramModifier = XlsxProgramModifier.of(
                localOutputStream.getInputStream(), dictionaryService);
        when(dictionaryService.getEnValue(eq(DictionaryName.goal_title),
                    anyString(), anyString())).thenReturn("Loose weight");
        xlsxProgramModifier.updateCellData(mock(OutputStream.class), new InUser()
                .setWeight(60F)
                .setInUserGoals(Arrays.asList(new InUserGoal(), new InUserGoal())));
    }

}
