package com.osomapps.pt.xlsx;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

@Slf4j
public class XlsxProgramParser {
    private final InputStream inputStream;

    private enum ScanMode {Strength, Cardio};

    private XlsxProgramParser(InputStream inputStream) {
       this.inputStream = inputStream; 
    }

    public static XlsxProgramParser of(InputStream inputStream) {
        return new XlsxProgramParser(inputStream);
    }

    public List<ExcelGoal> getExcelGoals() {
        final List<ExcelGoal> excelGoals = new ArrayList<>();
        try (final Workbook workbook = WorkbookFactory.create(inputStream)) {
            
            for (int index = 4; index < workbook.getNumberOfSheets(); index += 1) {
                final Sheet sheet = workbook.getSheetAt(index);
                final ExcelGoal excelGoal = new ExcelGoal()
                    .setSheetIndex(index)
                    .setName(sheet.getSheetName())
                    .setErrors(new ArrayList<>());
                final ScanMode scanMode;
                if ("Output".equals(getCellData(sheet, 13, 0))) {
                    scanMode = ScanMode.Strength;
                } else {
                    scanMode = ScanMode.Cardio;
                }
                String prevUserGroupName = "";
                String prevRoundName = "";
                String prevPartName = "";
                UserGroup userGroup = new UserGroup();
                Round round = new Round();
                Part part = new Part();
                for (int workoutIndex = 0; workoutIndex < 10; workoutIndex += 1) {
                    final String userGroupName = getNumberOrNullAsString(getCellData(sheet, 2, 2 + workoutIndex));
                    final String roundName = getNumberOrNullAsString(getCellData(sheet, 3, 2 + workoutIndex));
                    final String partName = (String) getCellData(sheet, 4, 2 + workoutIndex);
                    if (userGroupName != null && !prevUserGroupName.equals(userGroupName)) {
                        userGroup = new UserGroup().setName(userGroupName);
                        prevUserGroupName = userGroupName;
                        excelGoal.getUserGroups().add(userGroup);
                    }
                    if (roundName != null && !prevRoundName.equals(roundName)) {
                        round = new Round()
                            .setName(roundName);
                        prevRoundName = roundName;
                        userGroup.getRounds().add(round);
                    }
                    if (!prevPartName.equals(partName)) {
                        part = new Part()
                            .setName(partName);
                        prevPartName = partName;
                        round.getParts().add(part);
                    }
                    final String workoutName = new StringJoiner("_").add(excelGoal.getName())
                            .add(userGroup.getName()).add(round.getName()).add(part.getName()).toString();
                    final Workout workout = new Workout();
                    workout.setRowIndex(4);
                    workout.setColumnIndex(2 + workoutIndex);
                    workout.setName(workoutName);
                    final Optional<WarmupWorkoutItem> warmupWorkoutItem = extractWarmupWorkoutItem(sheet,
                            workoutIndex, scanMode, excelGoal, workoutName);
                    workout.setWarmup(warmupWorkoutItem.orElse(null));
                    for (int workoutItemIndex = 0; workoutItemIndex < 10; workoutItemIndex += 1) {
                        final int multiplyCoeff = scanMode == ScanMode.Strength ? 7 : 9;
                        if (!(getCellData(sheet, 10 + workoutItemIndex
                                * multiplyCoeff, 2 + workoutIndex) instanceof Number)) {
                            break;
                        }
                        final Optional<WorkoutItem> workoutItem = extractWorkoutItem(sheet, workoutItemIndex,
                                workoutIndex, scanMode, excelGoal, workoutName);
                        if (workoutItem.isPresent()) {
                            workout.getWorkoutItems().add(workoutItem.get());
                        }
                    }
                    part.getWorkouts().add(workout);
                }
                excelGoals.add(excelGoal);
            }
        } catch (IOException | InvalidFormatException ex) {
            log.error(ex.getMessage(), ex);
        }
        return excelGoals;
    }

    private Optional<WarmupWorkoutItem> extractWarmupWorkoutItem(Sheet sheet, int workoutIndex, ScanMode scanMode, ExcelGoal excelGoal, String workoutName) {
        final Optional<String> warmupName = getStringOrEmpty(getCellData(sheet, 5, 2 + workoutIndex));
        if (!warmupName.isPresent()) {
            excelGoal.getErrors().add("Warmup name not found. User " + excelGoal.getName() + ", workout " + workoutName + ".");
            return Optional.empty();
        }
        Integer speedInp = getIntegerOrNull(getCellData(sheet, 5 + 1, 2 + workoutIndex));
        Integer inclineInp = getIntegerOrNull(getCellData(sheet, 5 + 2, 2 + workoutIndex));
        Integer timeInp = extractNumbers(getCellData(sheet, 5 + 3, 2 + workoutIndex));
        return Optional.of(new WarmupWorkoutItem().setExercise(warmupName.get())
            .setSpeed(speedInp).setIncline(inclineInp).setTimeInMin(timeInp));
    }

    private Optional<WorkoutItem> extractWorkoutItem(final Sheet sheet, int workoutItemIndex,
            int workoutIndex, ScanMode scanMode, ExcelGoal excelGoal, String workoutName) {
        final int multiplyCoeff = scanMode == ScanMode.Strength ? 7 : 9;
        WorkoutItem workoutItem = new WorkoutItem();
        workoutItem.setRowIndex(4 + 4 + workoutItemIndex * multiplyCoeff);
        workoutItem.setColumnIndex(2 + workoutIndex);
        final Optional<String> exerciseName = getStringOrEmpty(getCellData(sheet, 5 + 4
                + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
        if (!exerciseName.isPresent()) {
            excelGoal.getErrors().add("Exercise name not found. User " + excelGoal.getName() + ", workout " + workoutName + ".");
            return Optional.empty();
        }
        Number setsInp = getNumberOrNull(getCellData(sheet, 5 + 5 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
        if (getIntegerOrNull(setsInp) == null || getIntegerOrNull(setsInp) == 0) {
            excelGoal.getErrors().add("Amount of sets cannot be 0. User " + excelGoal.getName() + ", workout " + workoutName + ".");
            return Optional.empty();
        }
        if (scanMode == ScanMode.Strength) {
            Number repetitionsInp = getNumberOrNull(getCellData(sheet, 5 + 6  + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
            String weightInp = getStringOrNull(getCellData(sheet, 5 + 7 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
            workoutItem.getInput().setExercise(exerciseName.orElse(null));
            workoutItem.getInput().setSets(getIntegerOrNull(setsInp));
            workoutItem.getInput().setRepetitions(getIntegerOrNull(repetitionsInp));
            workoutItem.getInput().setWeight(extractNumbers(weightInp));
        } else {
            String timeInp = getStringOrNull(getCellData(sheet, 5 + 6 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
            Number speedInp = getNumberOrNull(getCellData(sheet, 5 + 7 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
            String resistanceInp = getStringOrNull(getCellData(sheet, 5 + 8 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
            workoutItem.getInput().setExercise(exerciseName.orElse(null));
            workoutItem.getInput().setSets(getIntegerOrNull(setsInp));
            workoutItem.getInput().setTimeInMin(extractNumbers(timeInp));
            workoutItem.getInput().setSpeed(getIntegerOrNull(speedInp));
            workoutItem.getInput().setResistance(extractNumbers(resistanceInp));
        }
        return Optional.of(workoutItem);
    }

    private Number getNumberOrNull(Object object) {
        return object instanceof Number ? (Number) object : null;
    }

    private String getNumberOrNullAsString(Object object) {
        return object instanceof Number ? String.valueOf(((Number) object).intValue()) : null;
    }

    private Integer extractNumbers(Object object) {
        if (object instanceof String) {
            String onlyNumbersValue = ((String) object).replaceAll("[^\\d]+", "");
            return onlyNumbersValue.isEmpty() ? null : Integer.parseInt(onlyNumbersValue);
        }
        return null;
    }

    private String getStringOrNull(Object object) {
        return object instanceof String ? (String) object : null;
    }

    private Integer getIntegerOrNull(Object object) {
        return object instanceof Number ? ((Number) object).intValue() : null;
    }
    
    private Optional<String> getStringOrEmpty(Object object) {
        if (object instanceof String) {
            return Optional.of((String) object);
        }
        return Optional.empty();
    }

    private Object getCellData(Sheet sheet, int rowIndex, int cellIndex) {
        final Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return null;
        }
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
