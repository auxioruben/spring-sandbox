package org.jre.sandbox.webcalc.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.jre.sandbox.webcalc.core.Calculator;
import org.jre.sandbox.webcalc.core.CalcException;

import javax.validation.Valid;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * Handles requests for the application home page.
 */
@Controller

public class CalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(CalcController.class);
	
	/**
	 * Calculates the result of a given expression
	 */
	@RequestMapping(value = "/webcalc", method = RequestMethod.POST)
	public String calcResults(@ModelAttribute("calcForm") @Valid CalcModel cm, BindingResult result, Model model, Locale locale) {
		logger.info("Passed expression {}.", cm.getExpression());
		double ans;
		if (result.hasErrors()) {
			return "/webcalc/calc";
		}
		
		try {
			ans = Calculator.evaluate(cm.getExpression());
			if (Double.isInfinite(ans) || Double.isNaN(ans))
				throw new CalcException("Answer out of range.");
			cm.setResult(Double.toString(ans));
		}catch (CalcException e) {
			cm.setResult("");
			result.rejectValue("expression", "object.calcModel", e.getMessage());
		}
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("serverTime", dateFormat.format(date));	
		return "/webcalc/calc";
	}
	
	@RequestMapping(value = "/webcalc", method = RequestMethod.GET)
	public String displayCalc(Model model, Locale locale) {
		CalcModel cm = new CalcModel();    
	    model.addAttribute("calcForm", cm);
	    Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		model.addAttribute("serverTime", dateFormat.format(date));	
		return "/webcalc/calc";
	}	
}
