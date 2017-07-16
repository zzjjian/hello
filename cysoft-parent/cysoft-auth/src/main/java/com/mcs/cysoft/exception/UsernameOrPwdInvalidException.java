package com.mcs.cysoft.exception;

import com.mcs.cysoft.common.exception.ExceptionEnums;

public class UsernameOrPwdInvalidException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameOrPwdInvalidException(ExceptionEnums exceptionEnums) {
		super(exceptionEnums);
	}

}
