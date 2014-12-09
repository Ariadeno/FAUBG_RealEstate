package com.faubg.rea.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

import com.faubg.rea.Variables;
import com.faubg.rea.dao.ImageDao;
import com.faubg.rea.dao.OfferDao;
import com.faubg.rea.dao.PropertyDao;
import com.faubg.rea.dao.PropertyDaoImpl;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.Image;
import com.faubg.rea.model.Offer;
import com.faubg.rea.model.Property;
import com.faubg.rea.model.User;

@Controller
public class AccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccountController.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private PropertyDao propertyDao;
	@Autowired
	private OfferDao offerDao;
	@Autowired
	private ImageDao imageDao;
	private List<Property> propertyList;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String login(Locale locale, Model model, HttpServletRequest request) {
		Check.Login(model, request);
		User user = (User) request.getSession().getAttribute("User");
		if (user != null) {
			if (user.getIsAdmin()) {
				List<Property> properties = propertyDao
						.findAllRentalProperties();
				properties.addAll(propertyDao.findAllResaleProperties());
				model.addAttribute("properties", properties);
				//List<Offer> offers = offerDao.findAllOfers();
			//	model.addAttribute("offers", offers);\
				model.addAttribute("viewProperties", true);
				
				
				
			}
		}
		return "account";
	}

	@RequestMapping(value = "/adminPanel", method = RequestMethod.GET)
	public String adminPanel(Locale locale, Model model,
			HttpServletRequest request) {
		Check.Login(model, request);
		return "adminPanel";
	}
	
	@RequestMapping(value = "/adminPanel/viewOffers", method = RequestMethod.GET)
	public String viewOffers(Model model, HttpServletRequest request)
			{
		Check.Login(model, request);
		User user = (User) request.getSession().getAttribute("User");
		if (user != null) {
			if (user.getIsAdmin()) {
				List<Property> properties = propertyDao
						.findAllRentalProperties();
				properties.addAll(propertyDao.findAllResaleProperties());
				model.addAttribute("properties", properties);
				//List<Offer> offers = offerDao.findAllOfers();
			//	model.addAttribute("offers", offers);\
				model.addAttribute("viewProperties", false);
				
				
				
			}
		}
		return "account";
	}

	@RequestMapping(value = "/adminPanel/editProperty", method = RequestMethod.GET)
	public String editProperty(Model model, HttpServletRequest request,
			@RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		Property property = propertyDao.findPropertyByID(id);
		model.addAttribute("property", property.toEditHTML());
		return "editProperty";
	}

	@RequestMapping(value = "/adminPanel/updateProperty", method = RequestMethod.POST)
	public String updateProperty(Model model,
			@Valid @ModelAttribute("property") Property property,
			BindingResult result) {
		if (!result.hasFieldErrors()) {
			propertyDao.saveProperty(property);
		}
		return "redirect:/adminPanel";
	}

	@RequestMapping(value = "/adminPanel/addProperty", method = RequestMethod.POST)
	public String addProperty(
			@RequestParam(required = false, value = "rental") Boolean rental,
			Model model, HttpServletRequest request,
			@Valid @ModelAttribute("property") Property property,
			BindingResult result) {
		Boolean ticked;
		ticked = (rental == null) ? false : true;
		property.setRental(ticked);
		propertyDao.addProperty(property);
		String[] values = request.getParameterValues("images");
		for (String value : values) {
			if (!value.isEmpty()) {
				Image image = new Image(value, property);
				imageDao.addImage(image);
			}
		}
		return "redirect:/adminPanel";
	}

	@RequestMapping(value = "/buyrent", method = RequestMethod.GET)
	public String buyrentProperty(Model model, HttpServletRequest request,
			@RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		Property property = propertyDao.findPropertyByID(id);
		List<String> imagesSRC = new LinkedList<String>();
		Set<Image> propertyImages = property.getImages();
		for (Image image : propertyImages) {
			imagesSRC.add(image.getLocation());
		}
		model.addAttribute("property", property);
		model.addAttribute("propertyImages", imagesSRC);
		return "buyrentPage";
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
}
