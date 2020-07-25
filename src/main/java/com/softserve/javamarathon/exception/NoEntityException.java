package com.softserve.javamarathon.exception;

public class NoEntityException extends RuntimeException {
    public NoEntityException(String message) {
        super(message);
    }

    public NoEntityException() {
    }

    public NoEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
