package com.faubg.rea.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.User;

@Controller
public class RegisterController {
	@Autowired private UserDao userDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		return "register";
	}
	
	@RequestMapping(value = "/registerRequest", method = RequestMethod.POST)
	public String registerRequest(Model model, @Valid @ModelAttribute("user") User user, BindingResult result){
		String registrationMessage = "Registration unsuccesful";
		
		if(!result.hasFieldErrors()){
			try{
			userDao.addUser(user);
			registrationMessage = "Registration successful " + user.getEmail();
			} catch(Exception exception) {
				if (userDao.findByUsername(user.getUsername()) != null){
					registrationMessage = "User already exists";
				}
			}
		}
		
		model.addAttribute("registrationSuccess", registrationMessage);
		
		return "registerRequest";
	}
}
