package com.mcs.cysoft.service.impl;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mcs.cysoft.entity.TokenEntity;
import com.mcs.cysoft.entity.User;
import com.mcs.cysoft.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{
	
	@Autowired
	private RedisTemplate<String, TokenEntity> redisTemplate;

	@Override
	public TokenEntity createToken(User user) {
		// TODO Auto-generated method stub
		// token生成算法还需考虑
		String token = UUID.randomUUID().toString().replace("-", "");
		System.out.println(token);
		TokenEntity entity = new TokenEntity();
		entity.setAccess_token(token);
		entity.setUserName(user.getUserName());
		redisTemplate.boundValueOps(entity.getUserName()).set(entity, 1, TimeUnit.HOURS);
		return entity;
	}

	@Override
	public boolean checkToken(String token, String username) {
		// TODO Auto-generated method stub
		TokenEntity entity = redisTemplate.boundValueOps(username).get();
		if(entity != null){
			if(token.equals(entity.getAccess_token())){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
//		System.out.println(entity.getAccess_token());
//		return false;
	}

}
