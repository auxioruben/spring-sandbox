/* Bracket.java
 * CIS2168
 * John Edwardson
 */
package org.jre.sandbox.webcalc.core;

import java.util.regex.Pattern;

/**
 * Encapsulates behavior of grouping symbols.  Each bracket has
 * an associated left and right symbol.
 * @author John Edwardson
 */
public enum Bracket {
        ROUND ("(", ")"),
        SQUARE ("[", "]"),
        CURLY ("{", "}");
        
        private final String left;
        private final String right;
        
        Bracket(String l, String r) {
            left = l;
            right = r;
        }
        
        /**Determines if the token is an open bracket, and
         * returns the corresponding Bracket object
         * @param tok The token to test
         * @return The Bracket object corresponding to tok, or
         * null if tok is not a Bracket
         */
        public static Bracket isOpen(String tok) {
            Bracket result = null;
            for (Bracket b : values()) {
                if(tok.equals(b.left)) {
                    result = b;
                    break;
                }
            }
            return result;                
        }
        
        /**Determines if the token is a closed bracket, and
         * returns the corresponding Bracket object
         * @param tok The token to test
         * @return The Bracket object corresponding to tok, or
         * null if tok is not a Bracket
         */
        public static Bracket isClosed(String tok) {
            Bracket result = null;
            for (Bracket b : values()) {
                if(tok.equals(b.right)) {
                    result = b;
                    break;
                }
            }
            return result; 
        }
        
        public static String getPattern() {
        	String regex = "[";
        	for (Bracket b : values()) {
        		regex += Pattern.quote(b.left);
        		regex += Pattern.quote(b.right);
        	}
        	regex += "]";
        	return regex;
        }
}
