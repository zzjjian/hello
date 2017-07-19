package com.mcs.cysoft.auth.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mcs.cysoft.auth.entity.User;
import com.mcs.cysoft.auth.service.AccessTokenService;
import com.mcs.cysoft.common.entity.TokenEntity;
import com.mcs.cysoft.common.exception.ServiceException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

@Api(value="用户认证接口")
@RestController
public class LoginRestController {
	
	@Autowired
	AccessTokenService accessTokenService;

	@ApiOperation(value ="token生成", notes="认证用户信息并生成token")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, paramType = "body", dataType = "User") 
	@RequestMapping(value = "/v1/json/tickets", method = RequestMethod.POST)
    @Produces({"application/json"}) 
    public final TokenEntity createTicketForRainbow(@RequestBody User user) {
        	// 调用service验证用户并生成token
        	TokenEntity token = accessTokenService.createToken(user);
            return token;
    }
	
	@RequestMapping(value = "/v1/json/tickets/check", method = RequestMethod.GET)
    //@Produces({"application/json"}) 
    public final boolean createTicketForRainbow(HttpServletRequest request, HttpServletResponse response) {
		String access_token = request.getHeader("Authorization");
    	String user_name = request.getHeader("username");
        return accessTokenService.checkToken(access_token, user_name);
    }
	
	@ApiIgnore
	@RequestMapping(value = "/v1/json/test", method = RequestMethod.GET)
	public final ResponseEntity test(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println(request.getHeader("username"));
//		throw new Exception("one error llll");
		return ResponseEntity.ok("OK");
    }
	
	private String buildExceptionJSON(ServiceException se){ 
//		ExceptionEnums enums = se.getExceptionEnums(); 
//		return  "{'code':"+ enums.getCode()+",'message':'     "+enums.getDescription()+"'}"; 
		return null;
	} 
}
