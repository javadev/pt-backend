package com.osomapps.pt.tokenemail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile(
                    "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");

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
