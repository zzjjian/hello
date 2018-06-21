package com.mcs.cysoft.auth.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcs.cysoft.auth.entity.User;
import com.mcs.cysoft.auth.util.OAuthConstants;
import com.mcs.cysoft.auth.util.TokenGeneratorUtil;
import com.mcs.cysoft.common.entity.TokenEntity;

@Controller
public class LoginWebController {

	@RequestMapping("/login")
	public String index(Model model, final HttpServletRequest request, final HttpServletResponse response) {
		
		// 首先检查是否有sso登录用的cookie
		// 有cookie

		User user = new User();
//		user.setUserName("jan");
//		user.setPwd("jan");
		model.addAttribute("user", new User());
		return "login";

	}
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	public ModelAndView login(User user, final HttpServletRequest request, final HttpServletResponse response) throws Exception{

		// username and pwd check
		// 验证成功理应将用户跳转到相应页面
		final HttpSession session = request.getSession();
		String redirectUri = (String) session.getAttribute(OAuthConstants.REDIRECT_URI);
		
		String token = TokenGeneratorUtil.getToken();
		
		
		// 获取客户端需要回跳的url
//		String redirectUrl = redirectUri + "?access_token=" + token;
//		
//		System.out.println(user.getUserName() + " " + user.getPwd() + "cc");
//		
//		response.setContentType("text/plain");
//		final String text = String.format("%s=%s&%s=%s", OAuthConstants.ACCESS_TOKEN, token,
//                OAuthConstants.EXPIRES, "7200");
//		
//		try (PrintWriter printWriter = response.getWriter()) {
//            response.setStatus(HttpStatus.SC_OK);
//            printWriter.print(text);
//        } catch (final IOException e) {
////            LOGGER.error("Failed to write to response", e);
//        }
		
		if("jan".equals(user.getUserName())){
			final JsonFactory jsonFactory = new JsonFactory(new ObjectMapper());
			try (final JsonGenerator jsonGenerator = jsonFactory.createJsonGenerator(response.getWriter())) {
				response.setContentType("application/json");
				
				jsonGenerator.writeStartObject();
				jsonGenerator.writeStringField("access_token", token);
				jsonGenerator.writeEndObject();
			} finally {
				response.flushBuffer();
			}
		}
		// create json data
		
		
		
		
//		String finalUrl = redirectUri + "#" + "access_token=" + token + "&state=xyz&token_type=example&expires_in=3600";
//		
//		return new ModelAndView(new RedirectView(finalUrl));
		
		String callbackUrl = redirectUri + "?code=123456";
		final Map<String, Object> model = new HashMap<>();
        model.put("callbackUrl", callbackUrl);
		return new ModelAndView("confirm", model);

	}
	
	@RequestMapping(value= "/login/test", method = RequestMethod.POST)
	@ResponseBody
	@Produces({"application/json"})
	public TokenEntity loginForJson(User user, final HttpServletRequest request, final HttpServletResponse response) throws Exception{

		// username and pwd check
		// 验证成功理应将用户跳转到相应页面
		final HttpSession session = request.getSession();
		String redirectUri = (String) session.getAttribute(OAuthConstants.REDIRECT_URI);
		
		String token = TokenGeneratorUtil.getToken();
		
		TokenEntity entity = new TokenEntity();
		entity.setAccess_token(token);
		entity.setUser_name(user.getUserName());
		
		
        return entity;
		
	}
}
