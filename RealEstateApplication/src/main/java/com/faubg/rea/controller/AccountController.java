package com.faubg.rea.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.faubg.rea.dao.ImageDao;
import com.faubg.rea.dao.PropertyDao;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.Image;
import com.faubg.rea.model.Property;
import com.faubg.rea.model.User;

@Controller
public class AccountController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	private PropertyDao propertyDao;
	@Autowired
	private ImageDao imageDao;
	
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
	
	@RequestMapping(value = "/account/addProperty", method = RequestMethod.POST)
	public String addProperty(@RequestParam(required=false, value="rental") Boolean rental, Model model, HttpServletRequest request, @Valid @ModelAttribute("property") Property property, BindingResult result) {
		Boolean ticked;
		ticked = (rental == null) ? false : true;
		property.setRental(ticked);
		propertyDao.addProperty(property);
		String[] values = request.getParameterValues("images");
		for(String value : values){
			if(!value.isEmpty()){
				Image image = new Image(value, property);
				imageDao.addImage(image);
			}
		}
		return "redirect:/account";
	}
}
