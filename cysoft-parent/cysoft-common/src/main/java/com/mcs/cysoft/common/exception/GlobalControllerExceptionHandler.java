package com.mcs.cysoft.common.exception;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mcs.cysoft.common.entity.CommonErrorInfo;

@Configuration
@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public CommonErrorInfo handleServiceException(ServiceException ex) {
		String code = ex.getExceptionEnums().getCode();
		String des = ex.getExceptionEnums().getDescription();
		CommonErrorInfo err = new CommonErrorInfo(code, des);
		return err;
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST) // 400
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public CommonErrorInfo handleException(Exception ex) {
		String code = ExceptionEnums.INTERNAL_SERVER_ERROR.getCode();
//		String message = ex.getMessage();
		String message = ex.getLocalizedMessage();
		CommonErrorInfo err = new CommonErrorInfo(code, message);
		return err;
	}
}
