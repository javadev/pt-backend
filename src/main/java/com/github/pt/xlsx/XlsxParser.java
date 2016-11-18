package com.github.pt.xlsx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XlsxParser {
    private static final Logger LOG = LoggerFactory.getLogger(XlsxParser.class);
    private final InputStream inputStream;

    private XlsxParser(InputStream inputStream) {
       this.inputStream = inputStream; 
    }

    public static XlsxParser of(InputStream inputStream) {
        return new XlsxParser(inputStream);
    }

    public List<ExcelUser> getExcelUsers() {
        final List<ExcelUser> excelUsers = new ArrayList<>();
        final Workbook workbook;
        try {
            workbook = WorkbookFactory.create(inputStream);
            for (int index = 0; index < workbook.getNumberOfSheets(); index += 1) {
                final Sheet sheet = workbook.getSheetAt(index);
                if (sheet == null) {
                    continue;
                }
                final ExcelUser excelUser = new ExcelUser();
                excelUser.setName(sheet.getSheetName());
                for (int workoutIndex = 0; workoutIndex < 10; workoutIndex += 1) {
                    final String workoutName = (String) getCellData(sheet, 3, 2 + workoutIndex);
                    if (workoutName == null) {
                        break;
                    }
                    final Workout workout = new Workout();
                    workout.setName(workoutName);
                    for (int workoutItemIndex = 0; workoutItemIndex < 10; workoutItemIndex += 1) {
                        if (!(getCellData(sheet, 5 + workoutItemIndex * 7, 2 + workoutIndex) instanceof Number)) {
                            break;
                        }
                        final WorkoutItem workoutItem = extractWorkoutItem(sheet, workoutItemIndex, workoutIndex);
                        workout.getWorkoutItems().add(workoutItem);
                    }
                    excelUser.getWorkouts().add(workout);
                }
                excelUsers.add(excelUser);
            }
        } catch (IOException | InvalidFormatException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return excelUsers;
    }

    private WorkoutItem extractWorkoutItem(final Sheet sheet, int workoutItemIndex, int workoutIndex) {
        WorkoutItem workoutItem = new WorkoutItem();
        String exerciseName = (String) getCellData(sheet, 4 + workoutItemIndex * 7, 2 + workoutIndex);
        Number setsInp = (Number) getCellData(sheet, 5 + workoutItemIndex * 7, 2 + workoutIndex);
        Number repetitionsInp = (Number) getCellData(sheet, 6 + workoutItemIndex * 7, 2 + workoutIndex);
        String weightInp = getCellData(sheet, 7 + workoutItemIndex * 7, 2 + workoutIndex) == null ? null
                : getCellData(sheet, 7 + workoutItemIndex * 7, 2 + workoutIndex).toString();
        Number setsOut = (Number) getCellData(sheet, 8 + workoutItemIndex * 7, 2 + workoutIndex);
        Number repetitionsOut = (Number) getCellData(sheet, 9 + workoutItemIndex * 7, 2 + workoutIndex);
        String weightOut = getCellData(sheet, 10 + workoutItemIndex * 7, 2 + workoutIndex) == null ? null
                : getCellData(sheet, 10 + workoutItemIndex * 7, 2 + workoutIndex).toString();
        workoutItem.getInput().setExercise(exerciseName);
        workoutItem.getInput().setSets(setsInp.floatValue());
        workoutItem.getInput().setRepetitions(repetitionsInp.floatValue());
        workoutItem.getInput().setWeight(weightInp);
        if (setsOut != null) {
            workoutItem.getOutput().setSets(setsOut.floatValue());
        }
        if (repetitionsOut != null) {
            workoutItem.getOutput().setRepetitions(repetitionsOut.floatValue());
        }
        if (weightOut != null) {
            workoutItem.getOutput().setWeight(weightOut);
        }
        return workoutItem;
    }
    
    private Object getCellData(Sheet sheet, int rowIndex, int cellIndex) {
        final Row row = sheet.getRow(rowIndex);
        final Cell cell = row.getCell(cellIndex);
        return cell == null ? null : cellToObject(cell);
    }

    private Object cellToObject(Cell cell) {
        final int type = cell.getCellType();
        if (type == Cell.CELL_TYPE_STRING) {
            return cleanString(cell.getStringCellValue());
        }
		
        if (type == Cell.CELL_TYPE_BOOLEAN) {
            return cell.getBooleanCellValue();
        }
		
        if (type == Cell.CELL_TYPE_NUMERIC) {
            if (cell.getCellStyle().getDataFormatString().contains("%")) {
                return cell.getNumericCellValue() * 100;
            }
            return numeric(cell);
        }
		
        if (type == Cell.CELL_TYPE_FORMULA) {
            switch(cell.getCachedFormulaResultType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    return numeric(cell);
                case Cell.CELL_TYPE_STRING:
                    return cleanString(cell.getRichStringCellValue().toString());
            }
        }
        return null;
    }
    
    private String cleanString(String str) {
        return str.replace("\n", "").replace("\r", "");
    }
    
    private Object numeric(Cell cell) {
        if (HSSFDateUtil.isCellDateFormatted(cell)) {
            return cell.getDateCellValue();
        }
        return cell.getNumericCellValue();
    }

}
