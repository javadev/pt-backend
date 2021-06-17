package com.osomapps.pt.admin.certificate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import org.junit.Test;
import org.springframework.validation.MapBindingResult;

public class AmountOfDaysValidatorTest {

    @Test
    public void supports() {
        assertThat(new AmountOfDaysValidator().supports(Integer.class), equalTo(true));
    }

    @Test
    public void valid() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new AmountOfDaysValidator().validate(1, errors);
        assertThat(errors.getAllErrors().size(), equalTo(0));
    }

    @Test
    public void not_valid_null() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new AmountOfDaysValidator().validate(null, errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid_0() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new AmountOfDaysValidator().validate(0, errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid_366() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new AmountOfDaysValidator().validate(366, errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }
}
