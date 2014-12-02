package com.faubg.rea.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.faubg.rea.dao.ImageDao;
import com.faubg.rea.dao.PropertyDao;
import com.faubg.rea.dao.PropertyDaoImpl;
import com.faubg.rea.dao.UserDao;
import com.faubg.rea.model.Image;
import com.faubg.rea.model.Property;
import com.faubg.rea.model.User;

@Controller
@Transactional
public class AccountController {

	private static final Logger logger = LoggerFactory
			.getLogger(AccountController.class);

	@Autowired
	private UserDao userDao;
	@Autowired
	private PropertyDao propertyDao;
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
			@RequestParam("images") MultipartFile[] images, Model model,
			HttpServletRequest request,
			@Valid @ModelAttribute("property") Property property,
			BindingResult result) {
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
					}

					File dir = new File(rootPath + File.separator + "uploads");
					if (!dir.exists())
						dir.mkdirs();

					// Create the file on server
					String uniqueIdentifier = String.valueOf(System
							.currentTimeMillis());
					uniqueIdentifier = uniqueIdentifier
							.substring(uniqueIdentifier.length() - 5);
					String fileName = null;
					if (file.getOriginalFilename().toLowerCase()
							.contains(".jpg")) {
						fileName = file.getOriginalFilename().toLowerCase()
								.replace(".jpg", "")
								+ "_" + uniqueIdentifier + ".jpg";
					} else if (file.getOriginalFilename().toLowerCase()
							.contains(".png")) {
						fileName = file.getOriginalFilename().toLowerCase()
								.replace(".png", "")
								+ "_" + uniqueIdentifier + ".png";
					} else {
						fileName = file.getOriginalFilename().toLowerCase()
								+ "_" + uniqueIdentifier;
					}
					File serverFile = new File(dir.getAbsolutePath()
							+ File.separator + fileName);
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(serverFile));
					stream.write(bytes);
					stream.close();
					Image image = new Image("../resources/images/uploads/"
							+ fileName, property);
					imageDao.addImage(image);
					logger.info("Server File Location="
							+ serverFile.getAbsolutePath());

				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
		model.addAttribute("property", property.toEditHTML());
		return "editProperty";
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

	@RequestMapping(value = "/makeOffer", method = RequestMethod.GET)
	public String makeOffer(Model model, HttpServletRequest request,
			@RequestParam(value = "offer") Integer offer,
			@RequestParam(required = true, value = "id") Integer id) {
		Check.Login(model, request);
		Property property = propertyDao.findPropertyByID(id);
		if (offer != null) {
			return "redirect:/";
		}
		return "home";
	}

	public List<Property> getPropertyList() {
		return propertyList;
	}

	public void setPropertyList(List<Property> propertyList) {
		this.propertyList = propertyList;
	}
}
