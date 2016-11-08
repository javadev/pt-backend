package com.github.pt.xlsx;

import java.io.InputStream;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import org.junit.Test;

public class XlsxParserTest {
    private XlsxParser parser;
    
    @Test
    public void parse1() throws Exception {
        List<ExcelUser> excelUsers;
        try (InputStream stream = XlsxParserTest.class.getResourceAsStream("test-program1.xlsx")) {
            parser = XlsxParser.of(stream);
            excelUsers = parser.getExcelUsers();
        }
        assertThat(excelUsers.size(), equalTo(12));
        assertThat(excelUsers.get(0).getName(), equalTo("Styrke, Erfaren, Mann"));
        assertThat(excelUsers.get(0).getWorkouts().size(), equalTo(4));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getName(), equalTo("Fitness test"));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWorkoutItems().size(), equalTo(3));
        assertThat(excelUsers.get(0).getWorkouts().get(0).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Test exercise 1"));
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWorkoutItems().size(), equalTo(3));
        assertThat(excelUsers.get(0).getWorkouts().get(1).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Workout 1 exercise 1"));
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWorkoutItems().size(), equalTo(2));
        assertThat(excelUsers.get(0).getWorkouts().get(2).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Workout 2 exercise 1"));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWorkoutItems().size(), equalTo(2));
        assertThat(excelUsers.get(0).getWorkouts().get(3).getWorkoutItems()
                .get(0).getInput().getExercise(), equalTo("Workout 3 exercise 1"));
    }
}
