package com.mcs.cysoft.auth.exception;

import com.mcs.cysoft.common.exception.ExceptionEnums;
import com.mcs.cysoft.common.exception.ServiceException;

public class UsernameOrPwdInvalidException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsernameOrPwdInvalidException(ExceptionEnums exceptionEnums) {
		super(exceptionEnums, "用户名密码认证失败！");
	}

}
