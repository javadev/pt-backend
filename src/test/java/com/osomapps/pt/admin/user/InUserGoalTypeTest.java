package com.osomapps.pt.admin.user;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class InUserGoalTypeTest {
    @Test
    public void allArgsConstructor() {
        assertThat(new InUserGoalType(1L, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserGoalType inUserGoalType = new InUserGoalType();
        inUserGoalType.setId(null);
        inUserGoalType.setCreated(null);
        inUserGoalType.setD_user_goal_type(null);
        inUserGoalType.setInUserGoals(null);
        assertThat(inUserGoalType, notNullValue());
    }

    @Test
    public void getters() {
        InUserGoalType inUserGoalType = new InUserGoalType();
        inUserGoalType.getId();
        inUserGoalType.getCreated();
        inUserGoalType.getD_user_goal_type();
        inUserGoalType.getInUserGoals();
        assertThat(inUserGoalType, notNullValue());
    }

}
