package com.mcs.cysoft.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mcs.cysoft.entity.User;

@Controller
public class LoginWebController {

	@RequestMapping("/")
	public String index(Model model) {

		User user = new User();
		user.setUserName("jan");
		user.setPwd("jan");
		model.addAttribute("user", new User());
		return "login";

	}
	
	@RequestMapping("/login")
	public String login(User user, final HttpServletRequest request, final HttpServletResponse response) {

		final HttpSession session = request.getSession();
		
		System.out.println(user.getUserName() + " " + user.getPwd());
		return "login";

	}
}
