/* Operator.java
 * CIS 2168
 * John Edwardson
 */
package org.jre.sandbox.webcalc.core;

import java.util.regex.Pattern;



/**
 * Encapsulates behavior of arithmetic operators.  Each operator has
 * an associated string symbol, integer precedence and compute function.
 * New operations can easily be added to the enum, but make sure that the
 * last operator is the SKP operator, which is used as a placeholder for
 * open brackets.
 * @author John Edwardson
 */
public enum Operator {
        ADD ("+", 1) {@Override 
                      public double compute(double a, double b) {
                        return a + b; }
        },SUB ("-", 1) {@Override 
                        public double compute(double a, double b) {
                            return a - b; }
        }, MLT ("*", 2) {@Override 
                         public double compute(double a, double b) {
                            return a * b; }
        }, DIV ("/", 2) {@Override 
                         public double compute(double a, double b) throws CalcException  {
                            if (b == 0) throw new CalcException("Division By Zero");
        					return a / b; }
        }, POW ("^", 3) {@Override
        				 public double compute(double a, double b) {
        					return Math.pow(a, b); }        				
        }, SKP (null, 0) {@Override
                        public double compute(double a, double b) {
                            return 0;} 
        };
    
        public abstract double compute(double a, double b) throws CalcException;
        private final String sym;
        private final int prec;
        public static final String opreg = "[\\+\\-\\*\\^/]";
        Operator(String s, int p) {
            sym = s;
            prec = p;
        }    
        
        /**Finds the operator corresponding to a given
         * token string
         * @param tok The symbol for the operator to be found
         * @return An operator object corresponding to tok, or null if
         * tok is not a valid operator.
         */
        public static Operator find(String tok) {
            Operator res = null;
            for(Operator op: values()) {
                if(op.sym != null && op.sym.equals(tok) ) {
                    res = op;
                }
            }
            return res;
        }
        /**Determines operator precedence.        
         * @param op1 first operator
         * @param op2 second operator
         * @return true of op1 has higher precedence than op2, false
         * otherwise
         */
        public static boolean precedes(Operator op1, Operator op2)
        {
            return op1.prec > op2.prec;
        }
        
        @Override
        public String toString() {
            return sym;
        }        
        
        public static String getPattern() {
        	String regex = "[";
        	for (Operator op : values()) {
        		if (op.sym != null)
        			regex += Pattern.quote(op.sym);
        	}
        	regex += "]";
        	return regex;
        }
} 
