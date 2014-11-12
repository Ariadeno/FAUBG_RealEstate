package com.faubg.rea.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("loginSuccessAttr")
public class LoginController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {
		//TEMP
		String loginTitle = "Login";
		String accountUrl = "/rea/login";
		if(request.getSession().getAttribute("LoggedIn") != null){
			Boolean loggedIn = (Boolean)request.getSession().getAttribute("LoggedIn");
			if(loggedIn){
				loginTitle = "My Account";
				accountUrl = "/rea/account";
			} 
		}
		model.addAttribute("LoginTitle", loginTitle);
		model.addAttribute("AccountUrl", accountUrl);
		//TEMP
		return "login";
	}

	@RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
	public String loginRequest(Locale locale, Model model,
			@RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {
		User foundUser = userDao.findByUsername(username);
		if (foundUser != null) {
			if (foundUser.getPassword().equals(password)) {
				model.addAttribute("User", foundUser);
				request.getSession().setAttribute("LoggedIn", true);
				request.getSession().setAttribute("username", foundUser.getUsername());
			}
		}
		return "redirect:/";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("LoggedIn");
		request.getSession().removeAttribute("username");
		return "redirect:/";
	}
	
	
}
