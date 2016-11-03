package com.github.pt;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnauthorizedException extends RuntimeException {
    
    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
