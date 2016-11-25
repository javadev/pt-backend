package com.github.pt.admin.certificate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
class AmountOfDaysValidator implements Validator {
    @Override
    public boolean supports(final Class<?> aClass) {
        return Integer.class.equals(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        final Integer amountOfDays = (Integer) obj;
        if (amountOfDays == null) {
            errors.reject("amountOfDays", "Invalid empty amountOfDays");
            return;
        }
        if (amountOfDays < 1 || amountOfDays > 365) {
            errors.reject("amountOfDays", "Invalid amountOfDays value " + amountOfDays);
        }
    }
}
