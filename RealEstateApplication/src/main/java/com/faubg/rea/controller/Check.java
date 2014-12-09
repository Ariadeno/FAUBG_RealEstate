package com.faubg.rea.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.faubg.rea.model.User;

public abstract class Check {
	public static void Login(Model model, HttpServletRequest request) {
		String loginTitle = "Login";
		String accountUrl = "/login";
		boolean isAdmin = false;
		if (request.getSession().getAttribute("LoggedIn") != null) {
			Boolean loggedIn = (Boolean) request.getSession().getAttribute("LoggedIn");
			if (loggedIn) {
				loginTitle = "My Account";
				accountUrl = "/account";
				User user = (User) request.getSession().getAttribute("User");
				if (user != null) {
					isAdmin = user.getIsAdmin();
				}
			}
		}
		model.addAttribute("LoginTitle", loginTitle);
		model.addAttribute("AccountUrl", accountUrl);
		model.addAttribute("isAdmin", isAdmin);
	}
}
