package com.mcs.cysoft.common.entity;

public class CommonErrorInfo {

	private String code;
	
	private String description;
	
	public CommonErrorInfo(String code, String des){
		this.code = code;
		this.description = des;
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
