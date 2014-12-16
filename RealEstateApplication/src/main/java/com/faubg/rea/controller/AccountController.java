package com.faubg.rea.controller;

import java.awt.Desktop.Action;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
@Transactional
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	@Autowired
	ServletContext context;

	@Autowired
	private UserDao userDao;
	@Autowired
	private PropertyDao propertyDao;
	@Autowired
	private OfferDao offerDao;
	@Autowired
	private ImageDao imageDao;
	private List<Property> propertyList;
	@Transactional
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String login(@RequestParam(required = false, value = "occupied") boolean occupied, Locale locale, Model model, HttpServletRequest request) {
		Check.Login(model, request);
		User user = (User) request.getSession().getAttribute("User");
		String test = "";
		if (user != null) {
			if (user.getIsAdmin()) {
				List<Property> properties;
				if(occupied){
					test = "hello";
					properties = propertyDao.findAllOccupiedProperties();
				} else {
					properties = propertyDao.findAllRentalProperties();
					properties.addAll(propertyDao.findAllResaleProperties());
				}
				
				model.addAttribute("test", test);
				model.addAttribute("properties", properties);
				//List<Offer> offers = offerDao.findAllOfers();
				//model.addAttribute("offers", offers);
				model.addAttribute("viewProperties", true);
			}
		}
		
		List<Offer> offers = new LinkedList<Offer>();
		for (Offer offer : user.getOffers()) {
			offers.add(offer);
		}
		model.addAttribute("userOffers", offers);
		return "account";
	}
	
	@RequestMapping(value = "/account/viewOffers", method = RequestMethod.GET)
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
				List<Offer> offers = offerDao.findAllOfers();
				model.addAttribute("offers", offers);
				model.addAttribute("viewProperties", false);
			}
		}
		return "account";
	}
	
	@RequestMapping(value = "/account/acceptRefuse", method = RequestMethod.POST)
	public String acceptOrRefuse(Model model, HttpServletRequest request, @RequestParam String action,
			 @RequestParam(required = true, value = "id") Integer id)
	{
		Offer offer = offerDao.findOfferByID(id);
		
		if (action.equals("Accept")){
			
			offer.setStatus("Accepted");
		}
		if (action.equals("Refuse")){
			offer.setStatus("Refused");			
		}
		model.addAttribute("viewProperties", false);
		
		return viewOffers(model, request);
	}

	@RequestMapping(value = "/account/editProperty", method = RequestMethod.GET)
	public String editProperty(Model model, HttpServletRequest request, @RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		Property property = propertyDao.findPropertyByID(id);
		List<String> imagesSRC = new LinkedList<String>();
		Set<Image> propertyImages = property.getImages();
		for (Image image : propertyImages) {
			imagesSRC.add(image.getLocation());
		}
		model.addAttribute("propertyImages", imagesSRC);
		model.addAttribute("property", property.toEditHTML());
		return "editProperty";
	}
	
	@RequestMapping(value = "/account/deleteProperty", method = RequestMethod.GET)
	public String deleteProperty(Model model, HttpServletRequest request, @RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		Property property = propertyDao.findPropertyByID(id);
		propertyDao.deleteProperty(property);
		return "deleteProperty";
	}

	@RequestMapping(value = "/account/updateProperty", method = RequestMethod.POST)
	public String updateProperty(Model model, @Valid @ModelAttribute("property") Property property, BindingResult result) {
		if (!result.hasFieldErrors()) {
			propertyDao.saveProperty(property);
		}
		return "redirect:/account";
	}

	@RequestMapping(value = "/account/addProperty", method = RequestMethod.POST)
	public String addProperty(@RequestParam(required = false, value = "rental") Boolean rental, @RequestParam("images") MultipartFile[] images,
			Model model, HttpServletRequest request, @Valid @ModelAttribute("property") Property property, BindingResult result) {
		Boolean ticked;
		ticked = (rental == null) ? false : true;
		property.setRental(ticked);
		propertyDao.addProperty(property);
		for (MultipartFile file : images) {
			if (!file.isEmpty()) {
				try {
					byte[] bytes = file.getBytes();

					// Creating the directory to store file
					String rootPath = System.getProperty("catalina.home");
					if (!rootPath.contains("C:")) {
						rootPath = "/opt/bitnami/apache-tomcat/webapps/ROOT/resources/images/";
					} else {
						rootPath = rootPath.substring(0, rootPath.length() - 22) + "MainWithRoot\\wtpwebapps\\RealEstateApplication\\resources\\images";
					}

					File dir = new File(rootPath + File.separator + "uploads");
					if (!dir.exists())
						dir.mkdirs();
					// Create the file on server
					String uniqueIdentifier = String.valueOf(System.currentTimeMillis());
					uniqueIdentifier = uniqueIdentifier.substring(uniqueIdentifier.length() - 5);
					String fileName = null;
					if (file.getOriginalFilename().toLowerCase().contains(".jpg")) {
						fileName = file.getOriginalFilename().toLowerCase().replace(".jpg", "") + "_" + uniqueIdentifier + ".jpg";
					} else if (file.getOriginalFilename().toLowerCase().contains(".png")) {
						fileName = file.getOriginalFilename().toLowerCase().replace(".png", "") + "_" + uniqueIdentifier + ".png";
					} else {
						fileName = file.getOriginalFilename().toLowerCase() + "_" + uniqueIdentifier;
					}
					File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					Image image = new Image("../resources/images/uploads/" + fileName, property);
					imageDao.addImage(image);
					logger.info("Server File Location=" + serverFile.getAbsolutePath());

				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		
		model.addAttribute("property", property.toEditHTML());
		return "editProperty";
	}

	@RequestMapping(value = "/buyrent", method = RequestMethod.GET)
	public String buyrentProperty(Model model, HttpServletRequest request, @RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		Property property = propertyDao.findPropertyByID(id);
		List<String> imagesSRC = new LinkedList<String>();
		Set<Image> propertyImages = property.getImages();
		for (Image image : propertyImages) {
			imagesSRC.add(image.getLocation());
		}
		List<Offer> offers = new LinkedList<Offer>();
		for (Offer offer : property.getOffers()) {
			offers.add(offer);
		}
		model.addAttribute("id", property.getId());
		model.addAttribute("offers", offers);
		model.addAttribute("property", property);
		model.addAttribute("propertyImages", imagesSRC);
		return "buyrentPage";
	}

	@RequestMapping(value = "/makeOffer", method = RequestMethod.GET)
	public String makeOffer(Model model, HttpServletRequest request, @RequestParam(value = "offerAmount") Integer offerAmount,
			@RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		//get the property that is being viewed
		Property property = propertyDao.findPropertyByID(id);
		//get current user
		User user = (User) request.getSession().getAttribute("User");
		//get current time
		java.util.Date date= new java.util.Date();
		Timestamp currentTime = new Timestamp(date.getTime());
		//add the new offer
				Offer offer = new Offer();
				offer.setDate(currentTime);
				offer.setPrice(offerAmount);
				offer.setProperty(property);
				offer.setStatus("NotAccepted");
				offer.setUser(user);
				offerDao.addOffer(offer);
		
		model.addAttribute("user", user);
		model.addAttribute("property", property);
		model.addAttribute("offer", offerAmount);
		return "confirmation";
	}
	
	
	
	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
}
