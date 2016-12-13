package com.osomapps.pt.tokenemail;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class NameValidator implements Validator {
    @Override
    public boolean supports(final Class<?> aClass) {
        return String.class.equals(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        final String name = (String) obj;
        if (name == null || name.trim().isEmpty()) {
            errors.reject("name", "Invalid empty name");
            return;
        }
        if (name.trim().length() < 2) {
            errors.reject("name", "Name should be at least 2 characters");
            return;
        }
    }
}
