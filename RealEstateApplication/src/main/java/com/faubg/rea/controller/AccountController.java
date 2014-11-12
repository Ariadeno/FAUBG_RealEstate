package com.faubg.rea.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.User;

@Controller
public class AccountController {
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {
		//TEMP
		String loginTitle = "Login";
		String accountUrl = "/rea/login";
		User user;
		if(request.getSession().getAttribute("LoggedIn") != null){
			Boolean loggedIn = (Boolean)request.getSession().getAttribute("LoggedIn");
			if(loggedIn){
				loginTitle = "My Account";
				accountUrl = "/rea/account";
				String username = (String)request.getSession().getAttribute("username");
				user = userDao.findByUsername(username);
				model.addAttribute("User", user);
			}
		}
		model.addAttribute("LoginTitle", loginTitle);
		model.addAttribute("AccountUrl", accountUrl);
		//TEMP
		return "account";
	}
}
