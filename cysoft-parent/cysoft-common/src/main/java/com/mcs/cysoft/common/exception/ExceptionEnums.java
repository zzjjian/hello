package com.mcs.cysoft.common.exception;

public enum ExceptionEnums {
	
	// auth exception
	UNAUTH_ERROR("B0001", "用户名密码认证失败!"),
	
	// gateway exception
	HEADER_USERNAME_NULL_ERROR("B1001", "请求头必须传入用户名!"),
	
	// system internal error
	INTERNAL_SERVER_ERROR("B9000", "服务器内部错误!");
	
	private String code;
	private String description;
	
	ExceptionEnums(String code, String description){
		this.code = code;
		this.description = description;
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
