package com.mcs.cysoft.service;

import com.mcs.cysoft.common.entity.TokenEntity;
import com.mcs.cysoft.entity.User;

public interface AccessTokenService {

	public TokenEntity createToken(User user);
	
	public boolean checkToken(String token, String username);
}
