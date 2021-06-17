package com.osomapps.pt.reportweight;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;
import org.junit.Test;

public class InUserWeightTest {

    @Test
    public void createAllArgs() {
        assertThat(new InUserWeight(1L, LocalDateTime.now(), null, null), notNullValue());
    }

    @Test
    public void setters() {
        InUserWeight inUserWeight = new InUserWeight();
        inUserWeight.setId(1L);
        inUserWeight.setCreated(LocalDateTime.MAX);
        inUserWeight.setWeight(null);
        inUserWeight.setInUser(null);
        assertThat(inUserWeight, notNullValue());
    }

    @Test
    public void getters() {
        InUserWeight inUserWeight = new InUserWeight();
        inUserWeight.getId();
        inUserWeight.getCreated();
        inUserWeight.getInUser();
        inUserWeight.getWeight();
        assertThat(inUserWeight, notNullValue());
    }
}
