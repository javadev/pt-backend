package com.osomapps.pt;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";

    @Value("${logging.level.org.springframework.web}")
    private String loggingLevel;

    @Autowired private ErrorAttributes errorAttributes;

    @RequestMapping(value = PATH)
    ErrorDTO error(WebRequest webRequest, HttpServletResponse response) {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring.
        // Here we just define response body.
        return new ErrorDTO(response.getStatus(), getErrorAttributes(webRequest, loggingLevel));
    }

    public String getErrorPath() {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(WebRequest webRequest, String loggingLevel) {
        return errorAttributes.getErrorAttributes(webRequest, "DEBUG".equals(loggingLevel)
                ? ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE) : ErrorAttributeOptions.defaults());
    }
}
