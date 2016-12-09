package com.osomapps.pt.tokenemail;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator {
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@"
            + "((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    @Override
    public boolean supports(final Class<?> aClass) {
        return String.class.equals(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        final String email = (String) obj;
        if (email == null || email.trim().isEmpty()) {
            errors.reject("email", "Invalid empty email address");
            return;
        }
        final Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            errors.reject("email", "Invalid email address (" + email + ")");
        }
    }
}
