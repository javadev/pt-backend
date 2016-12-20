package com.osomapps.pt.xlsx;

import java.io.InputStream;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class XlsxProgramParserTest {
    private XlsxProgramParser parser;

    @Test
    public void parse1() throws Exception {
        List<ExcelGoal> excelGoals;
        try (InputStream stream = XlsxParserTest.class.getResourceAsStream("Dette_blir_algo.xlsx")) {
            parser = XlsxProgramParser.of(stream);
            excelGoals = parser.getExcelGoals();
        }
        assertThat(excelGoals.size(), equalTo(8));
        assertThat(excelGoals.get(0).getName(), equalTo("Loose weight"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().size(), equalTo(1));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getName(), equalTo("Loose weight_1_1_Part 1"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(5));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(5));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(1).getInput().getExercise(), equalTo("Bench Press"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(1).getInput().getWeight(), equalTo(1));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(5));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(2).getInput().getExercise(), equalTo("Pull Up"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(5));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(3).getInput().getExercise(), equalTo("Dips"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(5));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(4).getInput().getExercise(), equalTo("Plank"));
        assertThat(excelGoals.get(0).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(4).getInput().getTimeInMin(), equalTo(2));
        assertThat(excelGoals.get(7).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(5));
        assertThat(excelGoals.get(7).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelGoals.get(7).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getSets(), equalTo(3));
        assertThat(excelGoals.get(7).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getRepetitions(), equalTo(10));
        assertThat(excelGoals.get(7).getUserGroups().get(0).getRounds().get(0).getParts().get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getWeight(), equalTo(1));
    }

}
