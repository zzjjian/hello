package com.mcs.cysoft.exception;

import com.mcs.cysoft.common.exception.ExceptionEnums;

public class ServiceException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ExceptionEnums exceptionEnums;

	public ServiceException(ExceptionEnums exceptionEnums) {
		this.exceptionEnums = exceptionEnums;
	}

	public ExceptionEnums getExceptionEnums() {
		return exceptionEnums;
	}
}
