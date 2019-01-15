package com.springboot.study.exception;

public class ParamNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String param;

	public ParamNotFoundException() {
	}

	public ParamNotFoundException(String param) {
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
