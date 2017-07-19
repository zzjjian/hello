package com.mcs.cysoft.common.entity;

import java.io.Serializable;

public class TokenEntity implements Serializable{
	
	private static final long serialVersionUID = -1L;

	private String access_token;
	
	private String user_name;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	@Override
	public String toString(){
		return "{'access_token':" + this.getAccess_token() + ",'user_name':'" + this.getUser_name() + "'}";
	}
	
}
