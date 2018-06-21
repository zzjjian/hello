package com.mcs.cysoft.gateway.exception;

import com.mcs.cysoft.common.exception.ExceptionEnums;
import com.mcs.cysoft.common.exception.ServiceException;

public class HeaderUsernameNullException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public HeaderUsernameNullException(ExceptionEnums exceptionEnums) {
		super(exceptionEnums, exceptionEnums.getDescription());
	}

}
