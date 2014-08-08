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


/**
 * Handles requests for the application home page.
 */
@Controller

public class CalcController {
	
	private static final Logger logger = LoggerFactory.getLogger(CalcController.class);
	
	/**
	 * Calculates the result of a given expression
	 */
	@RequestMapping(value = "/eval", method = RequestMethod.POST)
	public String calcResults(@ModelAttribute("calcForm") @Valid CalcModel cm, BindingResult result, Model model) {
		logger.info("Passed expression {}.", cm.getExpression());
		double ans;
		if (result.hasErrors()) {
			return "calc";
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
		return "calc";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String displayCalc(Model model) {
		CalcModel cm = new CalcModel();    
	    model.addAttribute("calcForm", cm);
	    return "calc";
	}	
}
