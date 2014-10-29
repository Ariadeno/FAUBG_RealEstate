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

import com.faubg.rea.dao.AdminDao;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.Admin;
import com.faubg.rea.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("loginSuccessAttr")
public class LoginController {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AdminDao adminDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "login";
	}

	@RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
	public String loginRequest(Locale locale, Model model,
			@RequestParam String username, @RequestParam String password,
			HttpServletRequest request) {

		String returnMessage = "The username or password you entered is invalid.";

		User foundUser = userDao.findByUsername(username);
		if (foundUser == null) {
			Admin foundAdmin = adminDao.findByUsername(username);
			if (foundAdmin != null) {
				if (foundAdmin.getPassword().equals(password)) {
					returnMessage = "You have been successfully logged in.";
					request.getSession().setAttribute("User", foundAdmin);
				}
			}
		} else {
			if (foundUser.getPassword().equals(password)) {
				returnMessage = "You have been successfully logged in.";
				model.addAttribute("User", foundUser);
				request.getSession().setAttribute("User", foundUser);
			}
		}
		model.addAttribute("loginSuccessAttr", returnMessage);

		return "redirect:/";
	}

}
