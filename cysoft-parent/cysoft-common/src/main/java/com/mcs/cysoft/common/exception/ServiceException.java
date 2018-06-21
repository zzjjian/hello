package com.mcs.cysoft.common.exception;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String code;
	private String description;

	private ExceptionEnums exceptionEnums;

	public ServiceException(ExceptionEnums exceptionEnums) {
		this.exceptionEnums = exceptionEnums;
		this.code = exceptionEnums.getCode();
		this.description = exceptionEnums.getDescription();
	}
	
	public ServiceException(ExceptionEnums exceptionEnums, String message) {
		super(message);
		this.exceptionEnums = exceptionEnums;
		this.code = exceptionEnums.getCode();
		this.description = exceptionEnums.getDescription();
	}
	
	public ServiceException(ExceptionEnums exceptionEnums, String message, Throwable cause) {
		super(message, cause);
		this.exceptionEnums = exceptionEnums;
		this.code = exceptionEnums.getCode();
		this.description = exceptionEnums.getDescription();
	}

	public ExceptionEnums getExceptionEnums() {
		return exceptionEnums;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
