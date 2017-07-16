package com.mcs.cysoft.common.exception;

public enum ExceptionEnums {
	
	UNAUTH_ERROR("B0001", "认证不正确");
	
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
