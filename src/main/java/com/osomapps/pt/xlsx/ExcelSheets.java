package com.osomapps.pt.xlsx;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Getter
@Setter
@ToString
public class ExcelSheets {
    List<ExcelExercise> excelExercises = new ArrayList<>();
    List<ExcelGoal> excelGoals = new ArrayList<>();
}
