package com.faubg.rea.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.faubg.rea.connections.MailMailer;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.User;
import com.faubg.rea.security.PasswordHash;
import com.faubg.rea.security.tmpData;

@Controller
public class RegisterController {
	@Autowired
	private UserDao userDao;
	MailMailer mailMailer;

	public void setMailMailer(MailMailer mailMailer) {
		this.mailMailer = mailMailer;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model, HttpServletRequest request) {
		Check.Login(model, request);
		return "register";
	}

	@RequestMapping(value = "/verify/username/{username}/hash/{hash}", method = RequestMethod.GET)
	public String verify(Model model, HttpServletRequest request, @PathVariable("username") String username, @PathVariable("hash") String hash) {
		Check.Login(model, request);
		model.addAttribute("Username", username);
		model.addAttribute("Hash", hash);
		return "verify";
	}

    @Transactional
	@RequestMapping(value = "/verifyRequest", method = RequestMethod.POST)
	public String loginRequest(Locale locale, Model model, @RequestParam String username, @RequestParam String hash, HttpServletRequest request) {
		User foundUser = userDao.findByUsername(username);
		if (foundUser != null) {
			if (!foundUser.getIsVerified()) {
				if (hash.equals(foundUser.getPassword())) {
					foundUser.setIsVerified(true);
					userDao.update(foundUser);
					model.addAttribute("User", foundUser);
					request.getSession().setAttribute("LoggedIn", true);
					request.getSession().setAttribute("username", foundUser.getUsername());
					request.getSession().setAttribute("User", foundUser);
					return "redirect:/";
				}
			}
		}
		return "verify";
	}

	@RequestMapping(value = "/registerRequest", method = RequestMethod.POST)
	public String registerRequest(Model model, @Valid @ModelAttribute("user") User user, BindingResult result) {
		String registrationMessage = "Registration unsuccesful";
		if (!result.hasFieldErrors()) {
			try {
				userDao.addUser(user);
				registrationMessage = "Registration successful " + user.getUsername();
				AbstractApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
				setMailMailer((MailMailer) context.getBean("mailMail"));
				mailMailer
						.sendMail(
								"AUBG@Moridrin.com",
								user.getEmail(),
								"Registration AUBG",
								"You've registered at AUBG.Moridrin.com\nYou can login with:"
										+ "\nUsername:\t"
										+ user.getUsername()
										+ "\nPassword:\t"
										+ tmpData.getOriginalPassword()
										+ "\nClick AUBG.Moridrin.com/verify/username/"
										+ user.getUsername()
										+ "/hash/"
										+ user.getPassword()
										+ " to verify your account."
										+ "\n\nThis is one of those accounts you can not remove, and we will always have your email in our databases!!! Fear Us!!");
			} catch (Exception exception) {
				if (userDao.findByUsername(user.getUsername()) != null) {
					registrationMessage = "User already exists";
				}
			}
		}

		model.addAttribute("registrationSuccess", registrationMessage);

		return "login";
	}
}
