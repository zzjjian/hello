package com.mcs.cysoft.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserRedisEntity implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
	private String userId;
	
	private TokenEntity tokenEntity;
	
	private List<String> urlList = new ArrayList<String>();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public TokenEntity getTokenEntity() {
		return tokenEntity;
	}

	public void setTokenEntity(TokenEntity tokenEntity) {
		this.tokenEntity = tokenEntity;
	}

	public List<String> getUrlList() {
		return urlList;
	}

	public void setUrlList(List<String> urlList) {
		this.urlList = urlList;
	}
}
