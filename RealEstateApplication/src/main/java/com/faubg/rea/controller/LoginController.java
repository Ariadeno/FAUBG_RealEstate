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
		Check.Login(model, request);
		return "login";
	}

	@RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
	public String loginRequest(Locale locale, Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request) {
		User foundUser = userDao.findByUsername(username);
		if (foundUser != null) {
			if (foundUser.getPassword().equals(password)) {
				model.addAttribute("User", foundUser);
				request.getSession().setAttribute("LoggedIn", true);
				request.getSession().setAttribute("username", foundUser.getUsername());
				request.getSession().setAttribute("User", foundUser);
				return "redirect:/";
			}
		}
		return "redirect:/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().removeAttribute("LoggedIn");
		request.getSession().removeAttribute("username");
		request.getSession().removeAttribute("User");
		return "redirect:/";
	}

}
