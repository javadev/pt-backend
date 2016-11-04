package com.github.pt;

import java.util.Map;

public class ErrorDTO {

    public String error_description;

    public ErrorDTO(int status, Map<String, Object> errorAttributes) {
        this.error_description = (String) errorAttributes.get("message");
    }

}
