package com.faubg.rea.controller;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faubg.rea.dao.AdminDao;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.Admin;
import com.faubg.rea.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {

	@Autowired private UserDao userDao;
	@Autowired private AdminDao adminDao;
	//private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		/*
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
		*/
		
		//If not signed in, go to login.
		return login(locale, model);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/loginRequest", method = RequestMethod.POST)
	public String loginRequest(Locale locale, Model model, @RequestParam String username, @RequestParam String password) {
		
		String returnMessage = "The username or password you entered is invalid.";
		
		User foundUser = userDao.findByUsername(username);
		if(foundUser == null){
			Admin foundAdmin = adminDao.findByUsername(username);
			if (foundAdmin != null){
				if(foundAdmin.getPassword().equals(password)){
					returnMessage = "You have been successfully logged in.";
				}
			}
		} else {
			if(foundUser.getPassword().equals(password)){
				returnMessage = "You have been successfully logged in.";
			}
		}
		
		model.addAttribute("loginSuccess", returnMessage);
		
		return "loginRequest";
	}
	
}
