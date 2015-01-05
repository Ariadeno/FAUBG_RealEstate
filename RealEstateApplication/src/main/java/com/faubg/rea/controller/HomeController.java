package com.faubg.rea.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.tiles.request.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.support.DaoSupport;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.faubg.rea.model.Property;
import com.faubg.rea.model.User;
import com.faubg.rea.connections.MailMailer;
import com.faubg.rea.controller.HomeController;
import com.faubg.rea.dao.PropertyDao;
import com.faubg.rea.dao.UserDao;

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

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		Check.Login(model, request);
		return "home";
	}

	@RequestMapping(value = "/rent{id}", method = RequestMethod.GET)
	public String rent(@PathVariable("id") int pageNumber, @RequestParam(required = false, value = "orderBy") boolean orderBy,
			@RequestParam(required = false, value = "from") String priceFrom, @RequestParam(required = false, value = "to") String priceTo,
			Model model, HttpServletRequest request) {
		Check.Login(model, request);
		List<Property> properties = null;
		if (orderBy) {
			if ((!priceFrom.isEmpty()) && (!priceTo.isEmpty())) {
				properties = propertyDao.findAllRentalPropertiesWithinPriceRange(Integer.valueOf(priceFrom), Integer.valueOf(priceTo));
			}
		} else {
			properties = propertyDao.findAllRentalProperties();
		}
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageType", "/rent");
		model.addAttribute("properties", getElementsFromPage(properties, pageNumber));
		model.addAttribute("pages", pageChecker(properties));
		return "properties";
	}

	@RequestMapping(value = "/rent", method = RequestMethod.GET)
	public String rentRedirect(Model model, HttpServletRequest request) {
		return "redirect:/rent0";
	}

	@RequestMapping(value = "/buy{id}", method = RequestMethod.GET)
	public String buy(@PathVariable("id") int pageNumber, @RequestParam(required = false, value = "orderBy") boolean orderBy,
			@RequestParam(required = false, value = "from") String priceFrom, @RequestParam(required = false, value = "to") String priceTo,
			Model model, HttpServletRequest request) {
		Check.Login(model, request);
		List<Property> properties = null;
		if (orderBy) {
			if ((!priceFrom.isEmpty()) && (!priceTo.isEmpty())) {
				properties = propertyDao.findAllResalePropertiesWithinPriceRange(Integer.valueOf(priceFrom), Integer.valueOf(priceTo));
			}
		} else {
			properties = propertyDao.findAllResaleProperties();
		}
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageType", "/buy");
		model.addAttribute("properties", getElementsFromPage(properties, pageNumber));
		model.addAttribute("pages", pageChecker(properties));
		return "properties";
	}

	@RequestMapping(value = "/buy", method = RequestMethod.GET)
	public String buyRedirect(Model model, HttpServletRequest request) {
		return "redirect:/buy0";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String all(Model model, HttpServletRequest request) {
		Check.Login(model, request);
		List<Property> properties = propertyDao.findAllResaleProperties();
		properties.addAll(propertyDao.findAllRentalProperties());
		model.addAttribute("properties", properties);
		return "properties";
	}

	@RequestMapping(value = "/contact")
	public String contact(Model model, HttpServletRequest request) {
		Check.Login(model, request);
		return "contact";
	}

	@RequestMapping(value = "/register")
	public String registerRequest(Model model, HttpServletRequest request) {
		Check.Login(model, request);
		return "register";
	}

	@RequestMapping(value = "/search")
	public String searchRequest(Model model, @RequestParam String name,
			@RequestParam(value = "searchbartype", required = false, defaultValue = "1_ah") String searchbartype, HttpServletRequest request) {
		Check.Login(model, request);
		if (searchbartype.equals("for_sale")) {
			return "redirect:/buy";
		} else if (searchbartype.equals("for_rent")) {
			return "redirect:/rent";
		} else {
			return "redirect:/all";
		}
	}

	private int pageChecker(List<Property> properties) {
		int count = properties.size();
		int pages = (int) Math.ceil(count / 2.0);
		return pages;
	}

	private List<Property> getElementsFromPage(List<Property> properties, int page) {
		int pages = pageChecker(properties);
		List<Property> pageList = new ArrayList<Property>();
		int min = page * 2;
		int max = min + 2;
		for (int i = min; i < max; i++) {
			if (i < properties.size()) {
				pageList.add(properties.get(i));
			}
		}
		return pageList;
	}

	@RequestMapping(value = "/confirmation", method = RequestMethod.GET)
	public String confirmation(Locale locale, Model model, HttpServletRequest request) {
		Check.Login(model, request);
		return "confirmation";
	}

}
