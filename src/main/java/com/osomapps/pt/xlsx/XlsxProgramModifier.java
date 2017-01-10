package com.osomapps.pt.xlsx;

import com.osomapps.pt.dictionary.DictionaryName;
import com.osomapps.pt.dictionary.DictionaryService;
import com.osomapps.pt.programs.InProgram;
import com.osomapps.pt.programs.InWarmupWorkoutItem;
import com.osomapps.pt.programs.InWorkoutItem;
import com.osomapps.pt.token.InUser;
import com.osomapps.pt.token.InUserGoal;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_NUMERIC;
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
            fillCell(inputSheet, 2, 5, nullOrInteger(inUser.getD_level()));
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
            if (!inUser.getInPrograms().isEmpty()) {
                fillProgram(book.getSheetAt(2), inUser.getInPrograms().get(inUser.getInPrograms().size() - 1));
            }
            book.write(outputStream);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private void fillProgram(XSSFSheet programSheet, InProgram inProgram) {
        for (int index = 0; index < 15; index += 1) {
            fillCell(programSheet, 2 + index, 5, "");
        }
        for (int index = 0; index < inProgram.getInWorkouts().size(); index += 1) {
            fillCell(programSheet, 2 + index, 5, "Workout "
                    + (inProgram.getInWorkouts().get(index).getWorkout_index() + 1));
            final InWarmupWorkoutItem inWarmupWorkoutItem = inProgram.getInWorkouts().get(index)
                    .getInWarmupWorkoutItems().get(0);
            fillCell(programSheet, 2 + index, 7, inWarmupWorkoutItem.getD_exercise_name());
            fillCell(programSheet, 2 + index, 8, inWarmupWorkoutItem.getSpeed() + " km/h");
            fillCell(programSheet, 2 + index, 9, inWarmupWorkoutItem.getIncline() + "%");
            fillCell(programSheet, 2 + index, 10, (inWarmupWorkoutItem.getTime_in_sec() / 60) + " min");
            for (int index2 = 0; index2 < inProgram.getInWorkouts().get(index).getInWorkoutItems().size(); index2 += 1) {
                final InWorkoutItem inWorkoutItem = inProgram.getInWorkouts().get(index).getInWorkoutItems().get(index2);
                fillCell(programSheet, 2 + index, 11 + 7 * index2, inWorkoutItem.getD_exercise_name());
                fillCell(programSheet, 2 + index, 12 + 7 * index2, inWorkoutItem.getInWorkoutItemSets().size());
                if (inWorkoutItem.getInWorkoutItemSets().get(0).getRepetitions() != null) {
                    fillCell(programSheet, 2 + index, 13 + 7 * index2, inWorkoutItem.getInWorkoutItemSets()
                            .stream().map(set -> "" + set.getRepetitions()
                    ).collect(Collectors.joining(",")));
                }
                if (inWorkoutItem.getInWorkoutItemSets().get(0).getTime_in_sec() != null) {
                    fillCell(programSheet, 2 + index, 13 + 7 * index2, inWorkoutItem.getInWorkoutItemSets()
                            .stream().map(set -> round(set.getTime_in_sec() / 60F) + " min"
                    ).collect(Collectors.joining(",")));
                }
                if (inWorkoutItem.getInWorkoutItemSets().get(0).getWeight() != null) {
                    fillCell(programSheet, 2 + index, 14 + 7 * index2, inWorkoutItem.getInWorkoutItemSets()
                            .stream().map(set -> round(set.getWeight())
                    ).collect(Collectors.joining(",")));
                }
            }
        }
    }

    private String getGoalName(InUserGoal inUserGoal) {
        return Arrays.asList(dictionaryService.getEnValue(DictionaryName.goal_title,
                    inUserGoal.getD_goal_title(), null),
                dictionaryService.getEnValue(DictionaryName.goal_title_2,
                    inUserGoal.getD_goal_title_2(), null)).stream().filter(Objects::nonNull).collect(Collectors.joining(", "));
    }

    private Integer emptyOrInteger(Float value) {
        return value == null ? null : value.intValue();
    }

    private Integer nullOrInteger(String value) {
        return value == null ? null : Integer.parseInt(value);
    }

    private void fillCell(XSSFSheet sheet, int columnNumber, int rowNumber, String value) {
        XSSFRow row = sheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(columnNumber);
        if (cell == null) {
            cell = row.createCell(columnNumber);
        }
        cell.setCellValue(value == null ? "" : value);
        cell.setCellFormula(null);
    }

    private void fillCell(XSSFSheet sheet, int columnNumber, int rowNumber, Integer value) {
        XSSFRow row = sheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(columnNumber);
        if (cell == null) {
            cell = row.createCell(columnNumber, CELL_TYPE_NUMERIC);
        }
        if (value == null) {
            cell.setCellValue("");
        } else {
            cell.setCellValue(value);
        }
        cell.setCellFormula(null);
    }

    private String round(Float value) {
        if (value.floatValue() == value.intValue()) {
            return "" + value.intValue();
        }
        return "" + value;
    }
}
