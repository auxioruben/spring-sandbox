package org.jre.sandbox.webcalc.core;

import static org.junit.Assert.*;

import org.jre.sandbox.webcalc.core.CalcException;
import org.jre.sandbox.webcalc.core.Calculator;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.Test;

class testCase {
	public testCase(String input, String[] tokens, double result) {
		this.input = input;
		this.tokens = tokens;
		this.result = result;
	}
	public String input;
	public String[] tokens;
	public double result;
}

public class CalculatorTest {
	private double epsilon = 1e-6;
	private testCase[] cases = { new testCase("3  + 4 ", new String[]{"3","+","4"}, 7.0), 
			 new testCase("(4.2-3 )* 6", new String[]{"(", "4.2", "-", "3", ")", "*", "6"}, 7.2),
			 new testCase("2^3 * 0.5 * 9", new String[]{"2","^","3","*","0.5","*","9"}, 36), 
			 new testCase("[1 - 2] ^ {(63 - 1) / 2}", new String[]{"[","1","-","2","]","^","{","(","63","-","1",")","/","2","}"}, -1)};
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	String[] incorrect = {"7 - ", "(5 -2]"};
	
	@Test
	public void testGetTokens() {
		int i = 0;
		for (testCase c : cases) {
			assertArrayEquals("Test Case " + i + " failed", c.tokens, Calculator.parseInfix(c.input));
			i++;
		}
	}

	@Test
	public void testEval() throws CalcException {
		int i = 0;
		for (testCase c : cases) {
			try {
				assertEquals(c.result, Calculator.evaluate(c.input), epsilon);
			}catch(CalcException e) {
				fail("Test Case " + i + " threw exception: " + e.getMessage());
			}
			i++;
		}
		
		thrown.expect(CalcException.class);
		for (String s : incorrect) {
			Calculator.evaluate(s);
		}
	}
}
