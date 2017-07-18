package com.mcs.cysoft.auth.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mcs.cysoft.auth.entity.User;

@Controller
public class LoginWebController {

	@RequestMapping("/")
	public String index(Model model) {

		User user = new User();
//		user.setUserName("jan");
//		user.setPwd("jan");
		model.addAttribute("user", new User());
		return "login";

	}
	
	@RequestMapping(value= "/login", method = RequestMethod.POST)
	public String login(User user, final HttpServletRequest request, final HttpServletResponse response) {

//		final HttpSession session = request.getSession();
		// username and pwd check
		// 验证成功理应将用户跳转到相应页面
		
		System.out.println(user.getUserName() + " " + user.getPwd());
		return "login";

	}
}
