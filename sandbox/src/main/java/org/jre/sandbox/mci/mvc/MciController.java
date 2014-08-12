package org.jre.sandbox.mci.mvc;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MciController {
	
	private static final Logger logger = LoggerFactory.getLogger(MciController.class);
	
	@RequestMapping(value = {"/mci", "/mci/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("MCI home chosen.");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );	
		return "/mci/home";
	}
	
	@RequestMapping(value = "/mci/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		logger.info("MCI about chosen.");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );	
		return "/mci/about";
	}
	
	
	@RequestMapping(value = "/mci/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		logger.info("MCI admin chosen");
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "/mci/admin";
	}
	
	@RequestMapping(value = "/mci/user", method = RequestMethod.GET)
	public String userView(Locale locale, Model model) {
		logger.info("User GET chosen");
		
		User user = new User();    
	    model.addAttribute("userForm", user);
		return "/mci/user";
	}
	
	@RequestMapping(value = "/mci/user", method = RequestMethod.POST)
	public String userEdit(@ModelAttribute("userForm") @Valid User usr, BindingResult result, Model model) {
		logger.info("User POST chosen");
		if (result.hasErrors()) {
			return "/mci/user";
		}
		return "/mci/user";
	}
	
	@RequestMapping(value = "/mci/403")
	public String accessDenied(Principal user, Model model, Locale locale) {
		logger.info("Access Denied");
		
		if (user != null) 
			model.addAttribute("msg", "User: " + user.getName() + " is not authorized to view this page." );
		else
			model.addAttribute("msg", "You are not authorized to view this page." );
				
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "/mci/403";
	}
}
