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

    private XlsxProgramParser(InputStream inputStream) {
       this.inputStream = inputStream;
    }

    public static XlsxProgramParser of(InputStream inputStream) {
        return new XlsxProgramParser(inputStream);
    }

    public ExcelSheets getExcelSheets() {
        final List<ExcelExercise> excelExercises = new ArrayList<>();
        final List<ExcelGoal> excelGoals = new ArrayList<>();
        try (final Workbook workbook = WorkbookFactory.create(inputStream)) {

            excelExercises.addAll(extractExercises(workbook.getSheetAt(3)));
            for (int index = 4; index < workbook.getNumberOfSheets(); index += 1) {
                final Sheet sheet = workbook.getSheetAt(index);
                ExcelGoal excelGoal = extractGoal(index, sheet, excelExercises);
                if (!excelGoal.getUserGroups().isEmpty()) {
                    excelGoals.add(excelGoal);
                }
            }
        } catch (IOException | InvalidFormatException ex) {
            log.error(ex.getMessage(), ex);
        }
        return new ExcelSheets().setExcelExercises(excelExercises).setExcelGoals(excelGoals);
    }

    private List<ExcelExercise> extractExercises(Sheet sheet) {
        List<ExcelExercise> excelExercises = new ArrayList<>();
        for (int exerciseIndex = 2; exerciseIndex < sheet.getLastRowNum();
                exerciseIndex += 1) {
            ExcelExercise excelExercise = new ExcelExercise()
                    .setExercise_id(getIntegerOrNull(getCellData(sheet, exerciseIndex, 0)))
                    .setExercise_name(getStringOrNull(getCellData(sheet, exerciseIndex, 1)))
                    .setUser_group_1_percent(getIntegerOrNull(getCellData(sheet, exerciseIndex, 3)))
                    .setUser_group_2_percent(getIntegerOrNull(getCellData(sheet, exerciseIndex, 4)))
                    .setUser_group_3_percent(getIntegerOrNull(getCellData(sheet, exerciseIndex, 5)))
                    .setUser_group_4_percent(getIntegerOrNull(getCellData(sheet, exerciseIndex, 6)))
                    .setBasis_for_calculations(getStringOrNull(getCellData(sheet, exerciseIndex, 12)));
            if (excelExercise.getUser_group_1_percent() != null) {
                excelExercises.add(excelExercise);
            }
        }
        return excelExercises;
    }

    private ExcelGoal extractGoal(int index, final Sheet sheet, List<ExcelExercise> excelExercises) {
        final ExcelGoal excelGoal = new ExcelGoal()
                .setSheetIndex(index)
                .setName(sheet.getSheetName())
                .setLoops(getIntegerOrNull(getCellData(sheet, 0, 1)))
                .setErrors(new ArrayList<>());
        String prevUserGroupName = "";
        String prevRoundName = "";
        String prevPartName = "";
        UserGroup userGroup = new UserGroup();
        Round round = new Round();
        Part part = new Part();
        for (int workoutIndex = 0; workoutIndex < sheet.getRow(0).getPhysicalNumberOfCells();
                workoutIndex += 1) {
            if (!(getCellData(sheet, 10, 2 + workoutIndex) instanceof Number)) {
                break;
            }
            final String userGroupName = getNumberOrNullAsString(getCellData(sheet, 2, 2 + workoutIndex));
            final String roundName = getNumberOrNullAsString(getCellData(sheet, 3, 2 + workoutIndex));
            final String partName = (String) getCellData(sheet, 4, 2 + workoutIndex);
            final boolean userGroupNameWasCreated;
            if (userGroupName != null && !prevUserGroupName.equals(userGroupName)) {
                userGroup = new UserGroup().setName(userGroupName);
                prevUserGroupName = userGroupName;
                excelGoal.getUserGroups().add(userGroup);
                userGroupNameWasCreated = true;
            } else {
                userGroupNameWasCreated = false;
            }
            final boolean roundNameWasCreated;
            if (roundName != null && (userGroupNameWasCreated || !prevRoundName.equals(roundName))) {
                round = new Round()
                        .setName(roundName);
                prevRoundName = roundName;
                userGroup.getRounds().add(round);
                roundNameWasCreated = true;
            } else {
                roundNameWasCreated = false;
            }
            if (partName != null && (userGroupNameWasCreated || roundNameWasCreated || !prevPartName.equals(partName))) {
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
                    workoutIndex, excelGoal, workoutName);
            workout.setWarmup(warmupWorkoutItem.orElse(null));
            for (int workoutItemIndex = 0; workoutItemIndex < 10; workoutItemIndex += 1) {
                final int multiplyCoeff = 7;
                if (!(getCellData(sheet, 10 + workoutItemIndex
                        * multiplyCoeff, 2 + workoutIndex) instanceof Number)) {
                    break;
                }
                final Optional<WorkoutItem> workoutItem = extractWorkoutItem(sheet, workoutItemIndex,
                        workoutIndex, excelGoal, workoutName, excelExercises);
                if (workoutItem.isPresent()) {
                    workout.getWorkoutItems().add(workoutItem.get());
                }
            }
            part.getWorkouts().add(workout);
        }
        return excelGoal;
    }

    private String getOnlySymbols(String value) {
        return value.replace("(Weight loss)", "").replace("Leg Press strenght", "Legpress")
                .replace("Chest Press", "Bench Press").replace("Bike, Steady Pace Walk or Jog", "Bike, Steady Pace")
                .replaceAll("[\\s\\.\\,]+", "");
    }

    private Optional<WarmupWorkoutItem> extractWarmupWorkoutItem(Sheet sheet, int workoutIndex, ExcelGoal excelGoal, String workoutName) {
        final Optional<String> warmupName = getStringOrEmpty(getCellData(sheet, 5, 2 + workoutIndex));
        if (!warmupName.isPresent()) {
            excelGoal.getErrors().add("Warmup name not found. Goal " + excelGoal.getName() + ", workout " + workoutName + ".");
            return Optional.empty();
        }
        Integer speedInp = getIntegerOrNull(getCellData(sheet, 5 + 1, 2 + workoutIndex));
        Integer inclineInp = getIntegerOrNull(getCellData(sheet, 5 + 2, 2 + workoutIndex));
        Float timeInp = extractFloatNumbers(getCellData(sheet, 5 + 3, 2 + workoutIndex));
        return Optional.of(new WarmupWorkoutItem().setExercise(warmupName.get())
            .setSpeed(speedInp).setIncline(inclineInp).setTimeInMin(timeInp));
    }

    private Optional<WorkoutItem> extractWorkoutItem(final Sheet sheet, int workoutItemIndex,
            int workoutIndex, ExcelGoal excelGoal, String workoutName, List<ExcelExercise> excelExercises) {
        final int multiplyCoeff = 7;
        WorkoutItem workoutItem = new WorkoutItem();
        workoutItem.setRowIndex(4 + 4 + workoutItemIndex * multiplyCoeff);
        workoutItem.setColumnIndex(2 + workoutIndex);
        final Optional<String> exerciseName = getStringOrEmpty(getCellData(sheet, 5 + 4
                + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
        if (!exerciseName.isPresent()) {
            excelGoal.getErrors().add("Exercise name not found. Goal " + excelGoal.getName() + ", workout " + workoutName + ".");
            return Optional.empty();
        }
        Optional<ExcelExercise> excelExercise = excelExercises.stream().filter(exercise ->
            getOnlySymbols(exercise.getExercise_name()).equalsIgnoreCase(getOnlySymbols(exerciseName.get()))
        ).findFirst();
        if (!excelExercise.isPresent()) {
            excelGoal.getErrors().add("Exercise name (" + exerciseName.get() + ") not recognized. Goal "
                    + excelGoal.getName() + ", workout " + workoutName + ".");
        } else {
            workoutItem.setExerciseId(excelExercise.get().getExercise_id());
        }
        Number setsInp = getNumberOrNull(getCellData(sheet, 5 + 5 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
        Object repetitionsInp = getStringOrNumberOrNull(getCellData(sheet, 5 + 6  + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
        Object weightInp = getStringOrNumberOrNull(getCellData(sheet, 5 + 7 + workoutItemIndex * multiplyCoeff, 2 + workoutIndex));
        workoutItem.getInput().setExercise(exerciseName.orElse(null));
        if (repetitionsInp instanceof String || weightInp instanceof String) {
            workoutItem.getInput().setSets(new ArrayList<>());
            for (int index = 0; index < getIntegerOrNull(setsInp); index += 1) {
                InputSet inputSet = new InputSet();
                if (repetitionsInp instanceof String) {
                    String[] repetitionsInps = ((String) repetitionsInp).split("\\s*,\\s*");
                    if (exerciseName.orElse("").contains("Plank")
                            || repetitionsInps[index].contains("min")
                            || "Time".equalsIgnoreCase(getStringOrNull(getCellData(sheet, 5 + 6  + workoutItemIndex * multiplyCoeff, 1)))) {
                        inputSet.setTimeInMin(getFloatOrNull(extractFloatNumbers(repetitionsInps[index])));
                    } else {
                       inputSet.setRepetitions(getIntegerOrNull(extractNumbers(repetitionsInps[index])));
                    }
                } else {
                    if ("Time".equalsIgnoreCase(getStringOrNull(getCellData(sheet, 5 + 6  + workoutItemIndex * multiplyCoeff, 1)))) {
                        inputSet.setTimeInMin(getFloatOrNull(repetitionsInp));
                    } else {
                       inputSet.setRepetitions(getIntegerOrNull(repetitionsInp));
                    }
                }
                if (weightInp instanceof String) {
                    String[] weightInps = ((String) weightInp).split("\\s*,\\s*");
                    inputSet.setWeight(getFloatOrNull(extractFloatNumbers(weightInps[Math.min(index, weightInps.length - 1)])));
                } else {
                    inputSet.setWeight(getFloatOrNull(weightInp));
                }
                workoutItem.getInput().getSets().add(inputSet);
            }
        } else {
            InputSet inputSet = new InputSet();
            if ("Time".equalsIgnoreCase(getStringOrNull(getCellData(sheet, 5 + 6  + workoutItemIndex * multiplyCoeff, 1)))) {
                inputSet.setTimeInMin(getFloatOrNull(repetitionsInp));
            } else {
               inputSet.setRepetitions(getIntegerOrNull(repetitionsInp));
            }
            inputSet.setWeight(getFloatOrNull(weightInp));
            workoutItem.getInput().setSets(new ArrayList<>());
            for (int index = 0; index < getIntegerOrNull(setsInp); index += 1) {
                workoutItem.getInput().getSets().add(inputSet);
            }
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

    private Float extractFloatNumbers(Object object) {
        if (object instanceof String) {
            String onlyNumbersValue = ((String) object).replaceAll("[^\\d\\.]+", "");
            return onlyNumbersValue.isEmpty() ? null : Float.parseFloat(onlyNumbersValue);
        }
        return null;
    }

    private String getStringOrNull(Object object) {
        return object instanceof String ? (String) object : null;
    }


    private Object getStringOrNumberOrNull(Object object) {
        if (object instanceof String) {
            return (String) object;
        } else if (object instanceof Number) {
            return (Number) object;
        }
        return null;
    }

    private Integer getIntegerOrNull(Object object) {
        return object instanceof Number ? ((Number) object).intValue() : null;
    }

    private Float getFloatOrNull(Object object) {
        return object instanceof Number ? ((Number) object).floatValue() : null;
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
