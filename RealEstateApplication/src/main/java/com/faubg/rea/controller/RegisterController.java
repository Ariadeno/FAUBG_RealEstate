package com.faubg.rea.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.faubg.rea.connections.MailMailer;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.User;

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

	@RequestMapping(value = "/registerRequest", method = RequestMethod.POST)
	public String registerRequest(Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result) {
		String registrationMessage = "Registration unsuccesful";
		if (!result.hasFieldErrors()) {
			try {
				userDao.addUser(user);
				registrationMessage = "Registration successful "
						+ user.getEmail();
				AbstractApplicationContext context = new ClassPathXmlApplicationContext(
						"Spring-Mail.xml");
				setMailMailer((MailMailer) context.getBean("mailMail"));
				mailMailer
						.sendMail(
								"AUBG@Moridrin.com",
								user.getEmail(),
								"Registration AUBG",
								"You've registered at AUBG.Moridrin.com\nYou can login with:\nUsername:\t"
										+ user.getUsername()
										+ "\nPassword:\t"
										+ user.getPassword()
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
