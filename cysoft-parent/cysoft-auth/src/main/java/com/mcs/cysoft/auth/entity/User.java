package com.mcs.cysoft.auth.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户认证实体")
public class User {
	
	@ApiModelProperty(value = "登录用户名")
	private String userName;
	
	@ApiModelProperty(value = "登录密码")
	private String pwd;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
