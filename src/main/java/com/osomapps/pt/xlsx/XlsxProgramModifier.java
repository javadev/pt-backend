package com.osomapps.pt.xlsx;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class XlsxProgramModifier {
    private final InputStream inputStream;
    private final DictionaryService dictionaryService;

    private XlsxProgramModifier(InputStream inputStream,
            DictionaryService dictionaryService) {
       this.inputStream = inputStream;
       this.dictionaryService = dictionaryService;
    }

    public static XlsxProgramModifier of(InputStream inputStream,
            DictionaryService dictionaryService) {
        return new XlsxProgramModifier(inputStream, dictionaryService);
    }

    public void updateCellData(OutputStream outputStream, InUser inUser) {
        try (final XSSFWorkbook book = new XSSFWorkbook(inputStream)) {
            final XSSFSheet inputSheet = book.getSheetAt(0);
            fillCell(inputSheet, 2, 1, inUser.getD_sex());
            fillCell(inputSheet, 2, 2, emptyOrInteger(inUser.getAge()));
            fillCell(inputSheet, 2, 3, emptyOrInteger(inUser.getWeight()));
            fillCell(inputSheet, 2, 4, emptyOrInteger(inUser.getHeight()));
            fillCell(inputSheet, 2, 5, inUser.getD_level());
            if (inUser.getInUserGoals().size() > 0) {
                fillCell(inputSheet, 2, 7, getGoalName(inUser.getInUserGoals().get(0)));
            } else {
                fillCell(inputSheet, 1, 7, "");
                fillCell(inputSheet, 2, 7, "");
            }
            if (inUser.getInUserGoals().size() > 1) {
                fillCell(inputSheet, 2, 8, getGoalName(inUser.getInUserGoals().get(1)));
            } else {
                fillCell(inputSheet, 1, 8, "");
                fillCell(inputSheet, 2, 8, "");
            }
            inputStream.close();
            book.write(outputStream);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private String getGoalName(InUserGoal inUserGoal) {
        return Arrays.asList(dictionaryService.getEnValue(DictionaryName.goal_title,
                    inUserGoal.getD_goal_title(), null),
                dictionaryService.getEnValue(DictionaryName.goal_title_2,
                    inUserGoal.getD_goal_title_2(), null)).stream().filter(Objects::nonNull).collect(Collectors.joining(", "));
    }
    
    private String emptyOrInteger(Float value) {
        return value == null ? "" : "" + value.intValue();
    }

    private void fillCell(XSSFSheet sheet, int columnNumber, int rowNumber, String value) {
        XSSFRow row = sheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(columnNumber);
        cell.setCellValue(value == null ? "" : value);
        cell.setCellFormula(null);
    }
}
