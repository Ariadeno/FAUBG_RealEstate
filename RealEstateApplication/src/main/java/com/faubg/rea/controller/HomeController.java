package com.faubg.rea.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.request.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.faubg.rea.dao.PropertyDao;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.mail.MailMailer;
import com.faubg.rea.model.Property;
import com.faubg.rea.model.User;
import com.faubg.rea.controller.HomeController;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@Autowired
	private PropertyDao propertyDao;
	MailMailer mailMailer;

	public void setMailMailer(MailMailer mailMailer) {
		this.mailMailer = mailMailer;
	}

	/*
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(HomeController.class);
	 * 
	 * @Autowired private UserDao userDao;
	 * 
	 * @RequestMapping(value = "/home", method = RequestMethod.GET) public
	 * String login(Locale locale, Model model, HttpServletRequest request,
	 * 
	 * @ModelAttribute("loginSucessAttr") String success) { if
	 * (request.getSession().getAttribute("User") != null) { User currentUser =
	 * (User) request.getSession().getAttribute("User");
	 * model.addAttribute("accountEmail", currentUser.getEmail());
	 * logger.info(success); logger.info(currentUser.getEmail()); } return
	 * "home"; }
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		String loginTitle = "Login";
		String accountUrl = "/rea/login";
		if (request.getSession().getAttribute("LoggedIn") != null) {
			Boolean loggedIn = (Boolean) request.getSession().getAttribute(
					"LoggedIn");
			if (loggedIn) {
				loginTitle = "My Account";
				accountUrl = "/rea/account";
			}
		}
		model.addAttribute("LoginTitle", loginTitle);
		model.addAttribute("AccountUrl", accountUrl);
		return "home";
	}

	@RequestMapping(value = "/rent", method = RequestMethod.GET)
	public String rent(Model model, HttpServletRequest request) {
		List<Property> properties = propertyDao.findAllRentalProperties();
		model.addAttribute("rentalProperties", properties);
		return "rent";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String buy() {
		return "buy";
	}

	@RequestMapping(value = "/contact")
	public String contact() {
		return "contact";
	}

	@RequestMapping(value = "/register")
	public String registerRequest() {
		return "register";
	}

}
