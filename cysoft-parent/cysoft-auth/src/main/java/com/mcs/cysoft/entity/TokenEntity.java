package com.mcs.cysoft.entity;

import java.io.Serializable;

public class TokenEntity implements Serializable{
	
	private static final long serialVersionUID = -1L;

	private String access_token;
	
	private String userName;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
