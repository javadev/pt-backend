package com.osomapps.pt.xlsx;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public class XlsxModifier {
    private final InputStream inputStream;

    private XlsxModifier(InputStream inputStream) {
       this.inputStream = inputStream; 
    }

    public static XlsxModifier of(InputStream inputStream) {
        return new XlsxModifier(inputStream);
    }

    public void updateCellData(OutputStream outputStream, List<ExcelGoal> excelGoals) {
        try (final XSSFWorkbook book = new XSSFWorkbook(inputStream)) {
            
            for (ExcelGoal excelGoal : excelGoals) {
                for (UserGroup userGroup : excelGoal.getUserGroups()) {
                    for (Round round : userGroup.getRounds()) {
                        for (Part part : round.getParts()) {
                final XSSFSheet sheet = book.getSheetAt(excelGoal.getSheetIndex());
                            for (Workout workout : part.getWorkouts()) {
                    for (WorkoutItem workoutItem : workout.getWorkoutItems()) {
//                        fillCell(sheet, workoutItem.getColumnIndex(), workoutItem.getRowIndex() + 1,
//                                getIntegerAsStringOrNull(workoutItem.getInput().getSets()));
//                        fillCell(sheet, workoutItem.getColumnIndex(), workoutItem.getRowIndex() + 2,
//                                getIntegerAsStringOrNull(workoutItem.getInput().getRepetitions()));
//                        fillCell(sheet, workoutItem.getColumnIndex(), workoutItem.getRowIndex() + 3,
//                                getIntegerAsStringOrNull(workoutItem.getInput().getWeight()));
                        if (workoutItem.getOutput().getSets() == null) {
                            continue;
                        }
                        fillCell(sheet, workoutItem.getColumnIndex(), workoutItem.getRowIndex() + 4,
                                String.valueOf(workoutItem.getOutput().getSets()));
                        String repetitions = workoutItem.getOutput().getRepetitions().stream()
                                .map(Object::toString).collect(Collectors.joining(", "));
                        fillCell(sheet, workoutItem.getColumnIndex(), workoutItem.getRowIndex() + 5,
                                repetitions);
                        String weights = workoutItem.getOutput().getWeights().stream()
                                .map(weight -> weight == null ? null : weight.toString())
                                .filter(Objects::nonNull)
                                .collect(Collectors.joining(", "));
                        fillCell(sheet, workoutItem.getColumnIndex(), workoutItem.getRowIndex() + 6,
                                weights);
                    }                                
                            }
                        }
                    }
                }
            }
            inputStream.close();
            book.write(outputStream);
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private String getIntegerAsStringOrNull(Object object) {
        return object instanceof Number ? ((Number) object).toString() : null;
    }

    private void fillCell(XSSFSheet sheet, int columnNumber, int rowNumber, String value) {
        XSSFRow row = sheet.getRow(rowNumber);
        XSSFCell cell = row.getCell(columnNumber);
        cell.setCellValue(value == null ? "" : value);
        cell.setCellFormula(null);
    }
}
