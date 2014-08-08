/* Bracket.java
 * CIS2168
 * John Edwardson
 */
package org.jre.sandbox.webcalc.core;

/**
 * A simple exception class for handling Calculator errors
 * @author John Edwardson
 */
public class CalcException extends Exception {

    /**
     * Creates a new instance of <code>CalcException</code> without detail message.
     */
    public CalcException() {
    }

    /**
     * Constructs an instance of <code>CalcException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public CalcException(String msg) {
        super(msg);
    }
}
