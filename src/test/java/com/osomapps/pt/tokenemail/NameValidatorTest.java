package com.osomapps.pt.tokenemail;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import org.junit.Test;
import org.springframework.validation.MapBindingResult;

public class NameValidatorTest {

    @Test
    public void supports() {
        assertThat(new NameValidator().supports(String.class), equalTo(true));
    }

    @Test
    public void valid() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new NameValidator().validate("Name", errors);
        assertThat(errors.getAllErrors().size(), equalTo(0));
    }

    @Test
    public void not_valid_null() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new NameValidator().validate(null, errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid_empty() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new NameValidator().validate("", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid() {
        final MapBindingResult errors =
                new MapBindingResult(new HashMap<>(), String.class.getName());
        new NameValidator().validate("t", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }
}
