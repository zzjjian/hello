package com.mcs.cysoft.auth.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Produces;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mcs.cysoft.auth.entity.User;
import com.mcs.cysoft.auth.util.OAuthConstants;
import com.mcs.cysoft.auth.util.TokenGeneratorUtil;
import com.mcs.cysoft.common.entity.TokenEntity;

@Controller
public class OauthController {

	private static final Logger LOGGER = LoggerFactory.getLogger(OauthController.class);

	@RequestMapping("/oauth2.0/authorize")
	public ModelAndView oauth(final HttpServletRequest request, final HttpServletResponse response) {

		final String clientId = request.getParameter(OAuthConstants.CLIENT_ID);
		LOGGER.debug("{} : {}", OAuthConstants.CLIENT_ID, clientId);

		final String redirectUri = request.getParameter(OAuthConstants.REDIRECT_URI);
		LOGGER.debug("{} : {}", OAuthConstants.REDIRECT_URI, redirectUri);

		final String state = request.getParameter(OAuthConstants.STATE);
		LOGGER.debug("{} : {}", OAuthConstants.STATE, state);

		// clientId is required
		if (StringUtils.isBlank(clientId)) {
			LOGGER.error("Missing {}", OAuthConstants.CLIENT_ID);
			return new ModelAndView(OAuthConstants.ERROR_VIEW);
		}
		// redirectUri is required
		if (StringUtils.isBlank(redirectUri)) {
			LOGGER.error("Missing {}", OAuthConstants.REDIRECT_URI);
			return new ModelAndView(OAuthConstants.ERROR_VIEW);
		}
		
		// keep info in session
        final HttpSession session = request.getSession();
        session.setAttribute(OAuthConstants.REDIRECT_URI, redirectUri);
        session.setAttribute(OAuthConstants.CLIENT_ID, clientId);
        session.setAttribute(OAuthConstants.OAUTH20_STATE, state);
        
        String fullLoginUrl=request.getRequestURL().toString().replace("/oauth2.0/" + OAuthConstants.AUTHORIZE_URL, "/login");
		
		return new ModelAndView(new RedirectView(fullLoginUrl));
	}
	
	@RequestMapping(value= "/oauth2.0/accessToken", method = RequestMethod.GET)
	@ResponseBody
	@Produces({"application/json"})
	public TokenEntity getToken(final HttpServletRequest request, final HttpServletResponse response) throws Exception{

		final String redirectUri = request.getParameter(OAuthConstants.REDIRECT_URI);
        LOGGER.debug("{} : {}", OAuthConstants.REDIRECT_URI, redirectUri);

        final String clientId = request.getParameter(OAuthConstants.CLIENT_ID);
        LOGGER.debug("{} : {}", OAuthConstants.CLIENT_ID, clientId);

        final String code = request.getParameter(OAuthConstants.CODE);
        LOGGER.debug("{} : {}", OAuthConstants.CODE, code);
		
        // 此处应该获取code所关联的客户端ID和redirectUri跟当前的数据做比较
        // 如果不匹配，response直接getwriter写body数据返回
		
		// username and pwd check
		// 验证成功理应将用户跳转到相应页面
		final HttpSession session = request.getSession();
		
		String token = TokenGeneratorUtil.getToken();
		
		TokenEntity entity = new TokenEntity();
		entity.setAccess_token(token);
		entity.setUser_name("");
		
		// 生成sso用的cookie
		
		
		
        return entity;
		
	}
}
