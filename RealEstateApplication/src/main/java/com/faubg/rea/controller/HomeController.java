package com.faubg.rea.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tiles.request.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.support.DaoSupport;
import org.springframework.mail.MailMessage;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.faubg.rea.model.Country;
import com.faubg.rea.model.IpRange;
import com.faubg.rea.model.Property;
import com.faubg.rea.model.User;
import com.faubg.rea.connections.MailMailer;
import com.faubg.rea.dao.Language;
import com.faubg.rea.dao.PropertyDao;
import com.faubg.rea.dao.UserDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static Country country;
	
	@Autowired
	private PropertyDao propertyDao;
	MailMailer mailMailer;

	public void setMailMailer(MailMailer mailMailer) {
		this.mailMailer = mailMailer;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) throws IOException {
		Cookie[] cookie_jar = request.getCookies();
		String lang = "";
		String[] homeText = null;
		if (cookie_jar != null)
		{
			try {
				lang = cookie_jar[2].getValue();
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		System.out.println("value= " + lang);
		
		Check.Login(model, request);
		if (lang == "" || lang=="true") {
			homeText = testLanguage(getCountry(getIP()));
			model.addAttribute("selectedLang",getCountry(getIP()));
		}
		else {
			homeText = testLanguage(lang);
			model.addAttribute("selectedLang",lang);
		}

		model.addAttribute("text", homeText);
		model.addAttribute("languages", Language.values());
		return "home";
	}
	
	@RequestMapping(value = "/setLang", method = RequestMethod.POST)
	public String setCountry(Model model, @Valid @ModelAttribute("country") String newCountry, BindingResult result, HttpServletResponse response,@RequestParam("selectboxValue") String lang ) {
		 Cookie cookie = new Cookie("language", lang);
	     response.addCookie(cookie);	
		return "redirect:/";
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
		//if (Language.Dutch == true)
		for (Property p: properties) {
			p.setPrice(dollarToEuro(p.getPrice()));
		}
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("pageType", "/buy");
		model.addAttribute("properties", getElementsFromPage(properties, pageNumber));
		model.addAttribute("pages", pageChecker(properties));
		return "properties";
	}

	private Integer dollarToEuro(Integer price) {
		// TODO Auto-generated method stub
		Double euro = price * 0.7;
		int euroInteger = (int) Math.round(euro); 
		return euroInteger;
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
	
	private String getIP() throws IOException {
		URL whatismyip = new URL("http://checkip.amazonaws.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		return ip;
	}
	
	private String getCountry(String ip) throws IOException {
		URL whatismyip = new URL("http://ipinfo.io/" + ip + "/country");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String country = in.readLine(); //you get the IP as a String
		return country;
	}
	
	private String[] testLanguage(String language) throws IOException {
		String[] text = null;
		 	try{
		 		System.out.println(language);
		 		InputStream is = getClass().getClassLoader().getResourceAsStream("../language/"+ language + "_language.txt");
		        BufferedReader br = new BufferedReader(new InputStreamReader(is));
		 
		        StringBuilder sb = new StringBuilder();
		        String line = br.readLine();

		        while (line != null) {
		            sb.append(line);
		            sb.append(System.lineSeparator());
		            line = br.readLine();
		        }
		        String everything = sb.toString();  
		        System.out.println(everything);
		        
		        text = everything.split("-");
			    for (String s: text) {
			        System.out.println(s);
			    }
		 	}
		 	finally {}
			return text;
	}
		 	
}

