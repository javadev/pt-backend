package com.osomapps.pt.xlsx;

import java.io.InputStream;
import java.util.List;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class XlsxParserTest {
    private XlsxParser parser;
    
    @Test
    public void parse1() throws Exception {
        List<ExcelUser> excelUsers;
        try (InputStream stream = XlsxParserTest.class.getResourceAsStream("test-program2.xlsx")) {
            parser = XlsxParser.of(stream);
            excelUsers = parser.getExcelUsers();
        }
        assertThat(excelUsers.size(), equalTo(12));
        assertThat(excelUsers.get(0).getName(), equalTo("Styrke, Erfaren, Mann"));
        assertThat(excelUsers.get(0).getWorkouts().size(), equalTo(4));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getName(), equalTo("Workout 3"));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(4));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Squat"));
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWorkoutItems().size(), equalTo(5));
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWorkoutItems().size(), equalTo(5));
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWorkoutItems().size(), equalTo(5));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems().size(), equalTo(4));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Treadmill"));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getSets(), equalTo(1));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getTimeInMin(), equalTo(10));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getResistance(), equalTo(1));
    }

    @Test
    public void parse2() throws Exception {
        List<ExcelUser> excelUsers;
        try (InputStream stream = XlsxParserTest.class.getResourceAsStream("test-program3.xlsx")) {
            parser = XlsxParser.of(stream);
            excelUsers = parser.getExcelUsers();
        }
        assertThat(excelUsers.size(), equalTo(12));
        assertThat(excelUsers.get(0).getName(), equalTo("Styrke, Erfaren, Mann"));
        assertThat(excelUsers.get(0).getWorkouts().size(), equalTo(4));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getName(), equalTo("Workout 3"));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWarmup(), nullValue());
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(4));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Squat"));
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWarmup(), nullValue());
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWorkoutItems().size(), equalTo(5));
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWarmup(), nullValue());
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWorkoutItems().size(), equalTo(5));
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWarmup()
                .getExercise(), equalTo("Treadmill"));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWarmup()
                .getSpeed(), nullValue());
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWarmup()
                .getIncline(), equalTo(4));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWarmup()
                .getTimeInMin(), equalTo(10));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWorkoutItems().size(), equalTo(5));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Deadlift"));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems().size(), equalTo(4));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Treadmill"));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getSets(), equalTo(1));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getTimeInMin(), equalTo(10));
        assertThat(excelUsers.get(11).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getResistance(), equalTo(1));
    }

    @Test
    public void parse3() throws Exception {
        List<ExcelUser> excelUsers;
        try (InputStream stream = XlsxParserTest.class.getResourceAsStream("test-program4.xlsx")) {
            parser = XlsxParser.of(stream);
            excelUsers = parser.getExcelUsers();
        }
        assertThat(excelUsers.size(), equalTo(1));
        assertThat(excelUsers.get(0).getName(), equalTo("Ark1"));
        assertThat(excelUsers.get(0).getWorkouts().size(), equalTo(0));
    }
}
