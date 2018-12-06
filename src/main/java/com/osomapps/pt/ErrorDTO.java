package com.osomapps.pt;

import java.util.Map;

class ErrorDTO {

    public int status;
    public String error_description;

    ErrorDTO(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error_description = String.valueOf(errorAttributes.get("message"));
    }

}
