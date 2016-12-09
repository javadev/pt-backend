package com.osomapps.pt.reportweight;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class InUserWeightTest {
    
    @Test
    public void createAllArgs() {
        assertThat(new InUserWeight(
                1L, LocalDateTime.now(), null, null), notNullValue());
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
