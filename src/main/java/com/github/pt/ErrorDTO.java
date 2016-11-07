package com.github.pt;

import java.util.Map;

class ErrorDTO {

    public String error_description;

    ErrorDTO(int status, Map<String, Object> errorAttributes) {
        this.error_description = (String) errorAttributes.get("message");
    }

}
