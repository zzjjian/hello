package com.mcs.cysoft.web.controller;

import javax.ws.rs.Produces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcs.cysoft.entity.TokenEntity;
import com.mcs.cysoft.entity.User;

@RestController
public class LoginRestController {

	@RequestMapping(value = "/v1/json/tickets", method = RequestMethod.POST)
    @Produces({"application/json"}) 
    public final TokenEntity createTicketForRainbow(@RequestBody User user) {
        try{
        	// 调用service验证用户并生成token
        	TokenEntity token = new TokenEntity();
        	token.setAccess_token("aaaaaaawefaljs323iojsafj");
            return token;
        } catch (final Throwable e) {
        	TokenEntity token = new TokenEntity();
        	token.setAccess_token(null);
            return token;
        }
    }
}
