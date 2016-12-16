package com.osomapps.pt.tokenemail;

import java.util.Base64;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.equalTo;
import org.junit.Test;
import static org.junit.Assert.assertThat;
import org.springframework.validation.MapBindingResult;

public class DataurlValidatorTest {

    @Test
    public void supports() {
        assertThat(new DataurlValidator().supports(String.class), equalTo(true));
    }

    @Test
    public void valid() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new DataurlValidator().validate("data:image/gif;base64,"
                + "R0lGODlhEAAOALMAAOazToeHh0tLS/7LZv/0jvb29t/f3//Ub//ge8WSLf/rhf/3kdbW1mxsbP//mf///"
                + "yH5BAAAAAAALAAAAAAQAA4AAARe8L1Ekyky67QZ1hLnjM5UUde0ECwLJoExKcppV0aCcGCmTIHEIUEqjg"
                + "aORCMxIC6e0CcguWw6aFjsVMkkIr7g77ZKPJjPZqIyd7sJAgVGoEGv2xsBxqNgYPj/gAwXEQA7", errors);
        assertThat(errors.getAllErrors().size(), equalTo(0));
    }

    @Test
    public void valid_null() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new DataurlValidator().validate(null, errors);
        assertThat(errors.getAllErrors().size(), equalTo(0));
    }

    @Test
    public void not_valid_empty() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new DataurlValidator().validate("", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid_big_size() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new DataurlValidator().validate("data:image/gif;base64,"
                + Base64.getEncoder().encodeToString(new byte[2097153]), errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

    @Test
    public void not_valid() {
        final MapBindingResult errors = new MapBindingResult(new HashMap<>(), String.class.getName());
        new DataurlValidator().validate("test", errors);
        assertThat(errors.getAllErrors().size(), equalTo(1));
    }

}
