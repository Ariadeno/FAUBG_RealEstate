package com.faubg.rea.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;

import com.faubg.rea.dao.AdminDao;
import com.faubg.rea.dao.RegisteredNameDao;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.Admin;
import com.faubg.rea.model.RegisteredName;
import com.faubg.rea.model.User;

@Controller
public class RegisterController {
	@Autowired private RegisteredNameDao registeredNameDao;
	@Autowired private UserDao userDao;
	@Autowired private AdminDao adminDao;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String register(Model model){
		return "register";
	}
	
	@RequestMapping(value = "/adminRegister", method = RequestMethod.GET)
	public String AdminRegister(Model model){
		return "adminRegister";
	}
	
	@RequestMapping(value = "/registerRequest", method = RequestMethod.POST)
	public String registerRequest(Model model, @Valid @ModelAttribute("registeredname") RegisteredName registeredName, @Valid @ModelAttribute("user") User user, BindingResult result){
		String registrationMessage = "Registration unsuccesful";
		
		if(!result.hasFieldErrors()){
			try{
			registeredNameDao.addRegisteredName(registeredName);
			userDao.addUser(user);
			registrationMessage = "Registration successful " + user.getEmail();
			} catch(Exception exception) {
				if (registeredNameDao.findByUsername(user.getUsername()) != null){
					registrationMessage = "User already exists";
				}
			}
		}
		
		model.addAttribute("registrationSuccess", registrationMessage);
		
		return "registerRequest";
	}
	
	@RequestMapping(value = "/adminRegisterRequest", method = RequestMethod.POST)
	public String adminRegisterRequest(Model model, @Valid @ModelAttribute("registeredname") RegisteredName registeredName, @Valid @ModelAttribute("admin") Admin admin, BindingResult result){
		String registrationMessage = "Registration unsuccesful";
		
		if(!result.hasFieldErrors()){
			registeredNameDao.addRegisteredName(registeredName);
			adminDao.addAdmin(admin);
			registrationMessage = "Registration successful " + admin.getUsername();
		}
		
		model.addAttribute("registrationSuccess", registrationMessage);
		
		return "registerRequest";
	}
}
