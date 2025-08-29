package com.we2seek.restful_web_services.exception;

import java.io.Serial;
import java.io.Serializable;

//@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = 747799451743796834L;

    public UserNotFoundException(String s) {
        super(s);
    }
}
