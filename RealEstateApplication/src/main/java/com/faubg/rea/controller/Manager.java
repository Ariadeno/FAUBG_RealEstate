package com.faubg.rea.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.faubg.rea.model.Language;
import com.faubg.rea.model.Valuta;

public class Manager {

	public String getIP() throws IOException {
		URL whatismyip = new URL("http://checkip.amazonaws.com/");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		return ip;
	}
	
	public String getCountry(String ip) throws IOException {
		URL whatismyip = new URL("http://ipinfo.io/" + ip + "/country");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String country = in.readLine(); //you get the IP as a String
		return country;
	}
	
	public String[] testLanguage(String language) throws IOException {
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
		        
		        text = everything.split("-");
			    for (String s: text) {
			    }
		 	}
		 	finally {}
			return text;
	}
	
	public void cookies(Model model, HttpServletRequest request) throws IOException {
		Cookie[] cookie_jar = request.getCookies();
		String val = "";
		String lang = "";
		String[] homeText = null;
		if (cookie_jar != null)
		{
			try {
				for (Cookie c: cookie_jar){
					if(c.getName().equals("valuta")) {
						val = c.getValue();
					}
					if(c.getName().equals("language")) {
						lang = c.getValue();
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			val = "Dollar";
		}
		System.out.println("value= " + val);		
		if (val == "" || val=="true") {
			model.addAttribute("selectedVal","Dollar");
		}
		else {
			model.addAttribute("selectedVal",val);
		}

		if (lang == "" || lang=="true") {
			homeText = testLanguage(getCountry(getIP()));
			model.addAttribute("selectedLang",getCountry(getIP()));
		}
		else {
			homeText = testLanguage(lang);
			model.addAttribute("selectedLang",lang);
		}

		model.addAttribute("valuta", Valuta.values());
		model.addAttribute("text", homeText);
		model.addAttribute("languages", Language.values());
	}
	
	public String getValuta(Model model, HttpServletRequest request) {
		Cookie[] cookie_jar = request.getCookies();
		String val = "";
		if (cookie_jar != null)
		{
			try {
				for (Cookie c: cookie_jar){
					if(c.getName().equals("valuta")) {
						val = c.getValue();
					}
					System.out.println("name= " + c.getName());
					System.out.println("value= " + c.getValue());	
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		else {
			val = "Dollar";
		}
		System.out.println("value= " + val);		
		if (val == "" || val=="true") {
			model.addAttribute("selectedVal","Dollar");
		}
		else {
			model.addAttribute("selectedVal",val);
		}

		model.addAttribute("valuta", Valuta.values());
		
		return val;
	}
	
	public String rentCheck(boolean huur) {
		if (huur) {
			return "Yes";
		}
		else { return "No";}
	}
	
	public String priceCheck(int price, String valuta){
		if (valuta.equals("Euro")) {
			int newprice = (int) Math.abs(price * 0.89);
			return String.valueOf(newprice);
		}
		else {return String.valueOf(price);}
	}
}
