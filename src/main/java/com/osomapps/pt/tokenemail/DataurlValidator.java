package com.osomapps.pt.tokenemail;

import java.util.Base64;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class DataurlValidator implements Validator {
    private static final String BASE64_PREFIX = ";base64,";
    private static final int BASE64_PREFIX_LENGTH = 8;

    @Override
    public boolean supports(final Class<?> aClass) {
        return String.class.equals(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        final String dataUrl = (String) obj;
        if (dataUrl != null) {
            try {
                final String encodedString =
                        dataUrl.substring(dataUrl.indexOf(BASE64_PREFIX) + BASE64_PREFIX_LENGTH)
                                .replaceAll("\\s+", "");
                int length = Base64.getDecoder().decode(encodedString).length;
                if (length > 2097152) {
                    errors.reject("dataUrl", "Image cannot be large than 2 megabytes");
                }
            } catch (Exception ex) {
                errors.reject("dataUrl", "Invalid dataUrl");
            }
        }
    }
}
