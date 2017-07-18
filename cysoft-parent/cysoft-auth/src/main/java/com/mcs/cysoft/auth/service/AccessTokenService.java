package com.mcs.cysoft.auth.service;

import com.mcs.cysoft.auth.entity.User;
import com.mcs.cysoft.common.entity.TokenEntity;

public interface AccessTokenService {

	public TokenEntity createToken(User user);
	
	public boolean checkToken(String token, String username);
}
