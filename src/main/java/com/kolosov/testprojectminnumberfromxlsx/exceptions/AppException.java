package com.kolosov.testprojectminnumberfromxlsx.exceptions;

public abstract class AppException extends RuntimeException {
    public AppException(String message) {
        super(message);
    }
}
