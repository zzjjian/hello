package com.mcs.cysoft.service;

import com.mcs.cysoft.entity.TokenEntity;

public interface AccessTokenService {

	public TokenEntity createToken();
	
	public boolean checkToken();
}
