package com.springboot.study.exception;

public class ObjectAlreadyExistsException extends RuntimeException{

    private static final long serialVersionUID = 1L;
    private String param;

    public ObjectAlreadyExistsException() {
    }

    public ObjectAlreadyExistsException(String param) {
        super();
        this.param = param;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}
