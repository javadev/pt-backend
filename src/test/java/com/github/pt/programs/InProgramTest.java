package com.github.pt.programs;

import java.time.LocalDateTime;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Test;
import static org.junit.Assert.assertThat;

public class InProgramTest {
    @Test
    public void createAllArgs() {
        assertThat(new InProgram(
                1L, null, null, null, null, null), notNullValue());
    }

    @Test
    public void setters() {
        InProgram inProgram = new InProgram();
        inProgram.setCreated(LocalDateTime.MAX);
        assertThat(inProgram, notNullValue());
    }

    @Test
    public void getters() {
        InProgram inProgram = new InProgram();
        inProgram.getCreated();
        assertThat(inProgram, notNullValue());
    }
}
