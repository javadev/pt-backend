package com.github.pt.admin.certificate;

import java.util.HashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.springframework.validation.MapBindingResult;

public class CertificateValidatorTest {
    @Test
    public void supports() {
        assertThat(new CertificateValidator().supports(String.class), equalTo(true));
    }

    @Test
    public void valid() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new CertificateValidator().validate("ABCD1234", errors);
        assertThat(errors.getAllErrors().size(), equalTo(0));
    }

    @Test
    public void not_valid_null() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new CertificateValidator().validate(null, errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid_empty() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new CertificateValidator().validate(" ", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new CertificateValidator().validate("AAA", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

}
