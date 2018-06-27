package com.osomapps.pt.admin.program;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import org.junit.jupiter.api.Test;

public class ExerciseResponseTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new ExerciseResponse(null, null, null, null), notNullValue());
    }
    @Test
    public void setters() {
         ExerciseResponse exerciseResponse = new ExerciseResponse()
            .setId(null)
            .setExerciseId(null)
            .setNameEn(null)
            .setCardioPercent(null);
        assertThat(exerciseResponse, notNullValue());        
    }

    @Test
    public void getters() {
        ExerciseResponse exerciseResponse = new ExerciseResponse();
        exerciseResponse.getId();
        exerciseResponse.getExerciseId();
        exerciseResponse.getNameEn();
        exerciseResponse.getCardioPercent();
        assertThat(exerciseResponse, notNullValue());
    }

}
