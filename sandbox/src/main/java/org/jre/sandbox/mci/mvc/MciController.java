package org.jre.sandbox.mci.mvc;

import java.security.Principal;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MciController {
	
	private static final Logger logger = LoggerFactory.getLogger(MciController.class);
	private Date date;
	private DateFormat dateFormat;
	
	@RequestMapping(value = {"/mci", "/mci/home"}, method = RequestMethod.GET)
	public String home(Model model, Locale locale) {
		logger.info("MCI home chosen.");
		date = new Date();
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("serverTime", dateFormat.format(date));	
		return "/mci/home";
	}
	
	@RequestMapping(value = "/mci/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		logger.info("MCI about chosen.");
		date = new Date();
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("title", "About MCI");
		model.addAttribute("head", "Monolith Conglomerate International");
		model.addAttribute("msg", "We are a multinational firm that produces and sells a variety of useful goods and services.");
		model.addAttribute("serverTime", dateFormat.format(date));	
		return "/mci/message";
	}
	
	
	@RequestMapping(value = "/mci/admin", method = RequestMethod.GET)
	public String admin(Principal user, Locale locale, Model model) {
		logger.info("MCI admin chosen");
		date = new Date();
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("title", "MCI Administration");
		model.addAttribute("serverTime", dateFormat.format(date));
		if (user != null) 
			model.addAttribute("head", "Welcome, " + user.getName());
		model.addAttribute("msg", "This is the admin page.");
		return "/mci/message";
	}
	
	@RequestMapping(value = "/mci/user", method = RequestMethod.GET)
	public String userView(Principal user, Locale locale, Model model) {
		logger.info("User GET chosen");
		//User user = new User();    
	    //model.addAttribute("userForm", user);
		date = new Date();
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("title", "MCI User");
		model.addAttribute("serverTime", dateFormat.format(date));
		if (user != null) 
			model.addAttribute("head", "Welcome, " + user.getName());
		model.addAttribute("msg", "This is the user page.");
		return "/mci/message";
	}
	
//	@RequestMapping(value = "/mci/user", method = RequestMethod.POST)
//	public String userEdit(@ModelAttribute("userForm") @Valid User usr, BindingResult result, Model model) {
//		logger.info("User POST chosen");
//		if (result.hasErrors()) {
//			return "/mci/user";
//		}
//		return "/mci/user";
//	}
	
	@RequestMapping(value = "/mci/403")
	public String accessDenied(Principal user, Model model, Locale locale) {
		logger.info("Access Denied");
		date = new Date();
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("title", "Access Denied");
		model.addAttribute("head", "HTTP Status 403 - Verboten!");
		if (user != null) 
			model.addAttribute("msg", "User: " + user.getName() + " is not authorized to view this page." );
		else
			model.addAttribute("msg", "You are not authorized to view this page." );
		model.addAttribute("serverTime", dateFormat.format(date));
		return "/mci/message";
	}
	
	@RequestMapping(value = "/mci/invalidSession")
	public String invalidSession(Model model, Locale locale) {
		logger.info("Session Invalidated");
		date = new Date();
		dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("title", "Invalid Session");
		model.addAttribute("msg", "Invalid session.  Please login again." );
		model.addAttribute("serverTime", dateFormat.format(date));
		return "/mci/message";
	}
}
