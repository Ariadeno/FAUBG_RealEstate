package com.faubg.rea.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.User;
import com.faubg.rea.controller.HomeController;

/**
 * Handles requests for the application home page.
 */
@SessionAttributes("loginSuccessAttr")
@Controller
public class HomeController {
	
	/*private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request, @ModelAttribute("loginSucessAttr") String success) {
		if (request.getSession().getAttribute("User") != null) {
			User currentUser = (User) request.getSession().getAttribute("User");
			model.addAttribute("accountEmail", currentUser.getEmail());
			logger.info(success);
			logger.info(currentUser.getEmail());
		}
		return "home";
	}*/
	
	@RequestMapping(value="/")
    public String home() {
        return "home";
    }
	
	@RequestMapping(value="/login")
    public String login() {
        return "login";
    }
	
	@RequestMapping(value="/rent")
    public String rent() {
        return "rent";
    }
	
	@RequestMapping(value="/buy")
    public String buy() {
        return "buy";
    }
	
	@RequestMapping(value="/contact")
    public String contact() {
        return "contact";
    }
}
