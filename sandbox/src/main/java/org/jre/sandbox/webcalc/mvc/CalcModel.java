package org.jre.sandbox.webcalc.mvc;

import javax.validation.constraints.NotNull;

public class CalcModel {
	@NotNull
	private String expression;
	
	private String result;
	
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
}
