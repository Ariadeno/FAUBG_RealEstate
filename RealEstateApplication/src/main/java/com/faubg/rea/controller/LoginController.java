package com.faubg.rea.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
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
import com.faubg.rea.security.PasswordHash;
import com.sun.mail.handlers.message_rfc822;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("loginSuccessAttr")
public class LoginController {

	Manager m = new Manager();
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) throws IOException {
		Check.Login(model, request);
		m.cookies(model, request);
		return "login";
	}

	@RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
	public String loginRequest(Locale locale, Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request) {
		User foundUser = userDao.findByUsername(username);
		if (foundUser != null) {
			if (foundUser.getIsVerified()) {
				try {
					if (PasswordHash.validatePassword(password, foundUser.getPassword())) {
						model.addAttribute("User", foundUser);
						request.getSession().setAttribute("LoggedIn", true);
						request.getSession().setAttribute("username", foundUser.getUsername());
						request.getSession().setAttribute("User", foundUser);
						return "redirect:/";
					}
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
