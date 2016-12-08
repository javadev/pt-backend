package com.github.pt.tokenemail;

import java.util.HashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.springframework.validation.MapBindingResult;

public class EmailValidatorTest {

    @Test
    public void supports() {
        assertThat(new EmailValidator().supports(String.class), equalTo(true));
    }

    @Test
    public void valid() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new EmailValidator().validate("test@mail.com", errors);
        assertThat(errors.getAllErrors().size(), equalTo(0));
    }

    @Test
    public void not_valid_null() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new EmailValidator().validate(null, errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid_empty() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new EmailValidator().validate("", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new EmailValidator().validate("test", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

}
