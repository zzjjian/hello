package com.mcs.cysoft.gateway.exception;

import com.mcs.cysoft.common.exception.ExceptionEnums;
import com.mcs.cysoft.common.exception.ServiceException;

public class TestException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TestException(ExceptionEnums exceptionEnums) {
		super(exceptionEnums, exceptionEnums.getDescription());
	}
	
	public TestException(ExceptionEnums exceptionEnums, Throwable cause) {
		super(exceptionEnums, exceptionEnums.getDescription(), cause);
	}

}
