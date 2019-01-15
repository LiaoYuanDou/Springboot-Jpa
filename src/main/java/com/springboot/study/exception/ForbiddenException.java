package com.springboot.study.exception;

public class ForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String message;

    public ForbiddenException() {
    }

    public ForbiddenException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
