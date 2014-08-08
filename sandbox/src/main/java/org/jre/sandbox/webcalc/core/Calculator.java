/* Calculator.java
 * CIS 2168
 * John Edwardson
 * 
 */
package org.jre.sandbox.webcalc.core;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Defines functions to convert infix expressions to postfix expressions
 * and to evaluate these expressions.  The supported operations are  addition(+), subtraction(-), 
 * multiplication(*), and division(/).  The operands may be either double values, 
 * or string variable names.     
 * @author John Edwardson
 */
public class Calculator {
    private static Stack<Operator> opStack;    
    private static Stack<Bracket> brStack;    
    private static Stack<Integer> intStack;
    private static StringBuilder postfix;
    private static String[] intok; //Tokens for infix expression
    
    
    /**
     * Evaluates the given infix expression string.
     * The string may use any of the supported operations and operands
     * @param inexp The infix expression
     * @return The result of the calculation 
     * @throws CalcException if a syntax error occurs.
     */
    public static double evaluate(String infix) throws CalcException{
    	intok = parseInfix(infix);
    	return evalPostfix(infix2postfix(intok));
    }
    
    public static String[] parseInfix(String infix) {
    	String nextTok;
    	ArrayList<String> tokens = new ArrayList<String>();
    	Matcher m = Pattern.compile(Operator.getPattern() + "|" + Bracket.getPattern()).matcher(infix);
    	int i = 0;
    	while(m.find()) {
    		nextTok = infix.substring(i,m.start()).trim();
    		if (!nextTok.isEmpty()) {
    			tokens.add(infix.substring(i,m.start()).trim());
    			//System.out.println(nextTok);
    		}
    		i = m.end();
    		//System.out.println(m.group());
    		tokens.add(m.group());
    	}
    	if(i < infix.length()) {
    		nextTok = infix.substring(i).trim();
    		if (!nextTok.isEmpty()) {
    			tokens.add(infix.substring(i).trim());
    			//System.out.println(nextTok);
    		}
    	}
    	return tokens.toArray(new String[tokens.size()]);
    }
              
     /**
     * Evaluates the given Postfix expression.
     * @param exp A postfix expression using any of the supported operands
     * and operations, separated by spaces.
     * @return The result of the calculation  
     * @throws CalcException if the postfix expression is incorrect. 
     */
    public static double evalPostfix(String exp) throws CalcException {
        double x, y, z;               
        Operator op;
        Stack<Double> dstack = new Stack<Double>();
        String[] tokens = exp.split("\\s+");
        //Main calculation loop
        for (String tok : tokens) {                  
            try{//Push if tok is a double
                x = Double.valueOf(tok);
                dstack.push(x);
                continue;
            }catch(NumberFormatException e) {}            
            if ((op = Operator.find(tok)) != null) {            
            //If tok is an operation, perform the calculation on the top 2 elements of the stack
                try {
                    x = dstack.pop();
                    y = dstack.pop();
                  
                    z = op.compute(y,x);
                    dstack.push(z);                                            
                }catch (EmptyStackException e) {
                    throw new CalcException("Postfix syntax error: " + exp + "\n");                
                }                                             
            }else
                throw new CalcException("Postfix syntax Error: " + tok);
        }//Main Loop        
        
        if (dstack.size() != 1) 
            throw new CalcException("Postfix syntax error: " + exp);
        return dstack.pop();
    }

    /**
     * Converts infix expressions into postfix notation for calculation.
     * The result is stored in <code>this.postfix</code>
     * @param infix An infix expression using any of the supported operations
     * and operands
     * @throws CalcException In the case of a syntax error
     */
    private static String infix2postfix(String[] tokens) throws CalcException {       
        double c;
        String token;
        postfix = new StringBuilder();
        opStack = new Stack<Operator>();
        brStack = new Stack<Bracket>();
        intStack = new Stack<Integer>();
        Operator op;
        Bracket br;
        int i, rpos;
        for(i = 0; i < tokens.length; i++) {
            token = tokens[i];           
            if((op = Operator.find(token)) != null) {
                nextOp(op);
            }
            else if((br = Bracket.isOpen(token)) != null) {                        
                opStack.push(Operator.SKP);
                brStack.push(br);
                intStack.push(i);
            }
            else if((br = Bracket.isClosed(token)) != null){
                intStack.push(i);
                nextClosed(br);
            }else 
                try {
                    c = Double.valueOf(token);
                    postfix.append(token); postfix.append(" ");                 
                }catch (NumberFormatException e) {
                    throw new CalcException("Parse Error: " + token);          
                }
        }
        while (!opStack.empty()) {
            op = opStack.pop(); 
            if (op == Operator.SKP) {
                rpos = intStack.pop();
                throw new CalcException(String.format(
                                        "Mismatched Brackets: %s at position %d", 
                                        tokens[rpos], rpos+1 ));
            }                
            postfix.append(op); postfix.append(" ");
        }
        return postfix.toString();
    }
     
    /**
     * Processes the next closed bracket.
     * The result is stored in <code>this.postfix</code>
     * @param R A (closed) bracket corresponding to the last token
     * @throws CalcException if brackets are mismatched
     */
    private static void nextClosed(Bracket R) throws CalcException {                   
        int lpos = intStack.pop();
        if (brStack.empty()) {            
            throw new CalcException(String.format(
                                    "Mismatched Brackets: %s at position %d",
                                     intok[lpos], lpos+1));            
        }
            
        Bracket L = brStack.pop(); 
        int rpos = intStack.pop();
        if(!L.equals(R)){ //Checks for balanced parenthesis
            throw new CalcException(String.format(
                       "Mismatched Brackets:  %s ... %s at positions %d and %d", 
                        intok[rpos], intok[lpos], rpos+1, lpos+1));
        }
        //Loop until we hit an open parenthesis 
        while ( opStack.peek() != Operator.SKP )  {                             
                postfix.append(opStack.pop()); postfix.append(" "); }            
        opStack.pop();                                                               
    }
    
    /**
     * Uses operator precedence to determine whether to push the operator or update the postfix expression 
     * @param op The current operator
     * @throws CalcException in case of parenthesis errors
     */
   private static void nextOp(Operator op) throws CalcException {
        Operator topOp;                            
        while(!opStack.empty()) {
            topOp = opStack.peek();
            if (Operator.precedes(op, topOp))                              
                break;            
            else
                postfix.append(opStack.pop()); postfix.append(" ");                    
        }
        opStack.push(op);          
    }
}