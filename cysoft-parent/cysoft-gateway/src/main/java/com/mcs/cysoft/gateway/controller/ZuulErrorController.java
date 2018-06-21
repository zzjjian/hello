package com.mcs.cysoft.gateway.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mcs.cysoft.common.entity.CommonErrorInfo;
import com.mcs.cysoft.common.exception.ServiceException;

@RestController
public class ZuulErrorController extends AbstractErrorController {

	@Value("${error.path:/error}")
	private String errorPath;

	public ZuulErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@Override
	public String getErrorPath() {
		return errorPath;
	}

	@RequestMapping(value = "${error.path:/error}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<CommonErrorInfo> error(HttpServletRequest request) throws Exception {
		return getErrorMessage(request);
	}

	private ResponseEntity<CommonErrorInfo> getErrorMessage(HttpServletRequest request) throws Exception {
		final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
		if (exc.getCause() != null && exc.getCause() instanceof ServiceException) {
			throw (ServiceException) exc.getCause();
			// String code =
			// ((ServiceException)exc.getCause()).getExceptionEnums().getCode();
			// String des =
			// ((ServiceException)exc.getCause()).getExceptionEnums().getDescription();
			// return new CommonErrorInfo(code, des);
		} else {
			// return new CommonErrorInfo("500", exc.getMessage());
			// HttpStatus status = getStatus(request);
			// return ResponseEntity.status(status).body(new
			// CommonErrorInfo(ExceptionEnums.INTERNAL_SERVER_ERROR.getCode(),
			// exc.getMessage()));
			throw (Exception) exc;
		}
		// return exc != null ? exc.getMessage() : "Unexpected error occurred";
	}

}
