package com.osomapps.pt.admin.certificate;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
class CertificateValidator implements Validator {
    private static final Pattern CERTIFICATE_PATTERN = Pattern.compile("^[A-Z]{4}[0-9]{4}$");
    @Override
    public boolean supports(final Class<?> aClass) {
        return String.class.equals(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        final String certificate = (String) obj;
        if (certificate == null || certificate.trim().isEmpty()) {
            errors.reject("certificate", "Invalid empty certificate");
            return;
        }
        final Matcher matcher = CERTIFICATE_PATTERN.matcher(certificate);
        if (!matcher.matches()) {
            errors.reject("certificate", "Invalid certificate code (" + certificate + ")");
        }
    }
}
