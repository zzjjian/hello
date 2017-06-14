package com.mcs.cysoft.rest.controller;

import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcs.cysoft.entity.TokenEntity;
import com.mcs.cysoft.entity.User;
import com.mcs.cysoft.service.AccessTokenService;

@RestController
public class LoginRestController {
	
	@Autowired
	AccessTokenService accessTokenService;

	@RequestMapping(value = "/v1/json/tickets", method = RequestMethod.POST)
    @Produces({"application/json"}) 
    public final TokenEntity createTicketForRainbow(@RequestBody User user) {
        try{
        	// 调用service验证用户并生成token
        	TokenEntity token = accessTokenService.createToken();
            return token;
        } catch (final Throwable e) {
        	TokenEntity token = new TokenEntity();
        	token.setAccess_token(null);
            return token;
        }
    }
	
	@RequestMapping(value = "/v1/json/tickets/check", method = RequestMethod.GET)
    @Produces({"application/json"}) 
    public final boolean createTicketForRainbow() {
        return accessTokenService.checkToken();
    }
}
