package com.mcs.cysoft.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.authenticator.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mcs.cysoft.entity.TokenEntity;
import com.mcs.cysoft.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{
	
	@Autowired
	private RedisTemplate<String, TokenEntity> redisTemplate;

	@Override
	public TokenEntity createToken() {
		// TODO Auto-generated method stub
		String token = UUID.randomUUID().toString().replace("-", "");
		System.out.println(token);
		TokenEntity entity = new TokenEntity();
		entity.setAccess_token(token);
		entity.setUserName("jan.zhang");
		redisTemplate.boundValueOps(entity.getUserName()).set(entity, 1, TimeUnit.HOURS);
		return entity;
	}

	@Override
	public boolean checkToken() {
		// TODO Auto-generated method stub
		TokenEntity entity = redisTemplate.boundValueOps("jan.zhang").get();
		System.out.println(entity.getAccess_token());
		return false;
	}

}
