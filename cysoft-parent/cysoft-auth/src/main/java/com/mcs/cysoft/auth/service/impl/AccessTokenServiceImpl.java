package com.mcs.cysoft.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mcs.cysoft.auth.entity.User;
import com.mcs.cysoft.auth.exception.UsernameOrPwdInvalidException;
import com.mcs.cysoft.auth.service.AccessTokenService;
import com.mcs.cysoft.auth.util.TokenGeneratorUtil;
import com.mcs.cysoft.common.entity.TokenEntity;
import com.mcs.cysoft.common.entity.UserRedisEntity;
import com.mcs.cysoft.common.exception.ExceptionEnums;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{
	
	@Autowired
	private RedisTemplate<String, UserRedisEntity> redisTemplate;

	@Override
	public TokenEntity createToken(User user) {
		// TODO Auto-generated method stub
		// token生成算法还需考虑
		// 调用用户认证接口认证用户，认证通过返回用户信息和权限信息（所有可访问url列表）
		String token = TokenGeneratorUtil.getToken();
		TokenEntity entity = new TokenEntity();
		entity.setAccess_token(token);
		entity.setUserName(user.getUserName());
		
		if("jan.zhang".equals(user.getUserName())){
			throw new UsernameOrPwdInvalidException(ExceptionEnums.UNAUTH_ERROR);
		}
		
		// redis hash值存储用户信息和权限访问列表
		UserRedisEntity userEntity = new UserRedisEntity();
		userEntity.setUserId(123456L);
		List<String> urlList = new ArrayList<String>();
		urlList.add("/aut/**");
		userEntity.setUrlList(urlList);
		userEntity.setTokenEntity(entity);
			
		redisTemplate.boundValueOps(entity.getUserName()).set(userEntity, 1, TimeUnit.HOURS);
		// redisTemplate.boundHashOps(entity.getUserName()).
		
		return entity;
	}

	@Override
	public boolean checkToken(String token, String username) {
		UserRedisEntity entity = redisTemplate.boundValueOps(username).get();
		if(entity != null){
			if(token.equals(entity.getTokenEntity().getAccess_token())){
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}