package com.tpe.exception;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ConflictException extends RuntimeException {
    public ConflictException(String s) {
        super(s);
    }
}
