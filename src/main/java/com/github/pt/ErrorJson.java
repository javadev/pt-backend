package com.github.pt;

import java.util.Map;

public class ErrorJson {

    public String error_description;

    public ErrorJson(int status, Map<String, Object> errorAttributes) {
        this.error_description = (String) errorAttributes.get("message");
    }

}
