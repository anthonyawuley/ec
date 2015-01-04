/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;


/**
 *
 * @author anthony
 */

public class InitializationException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3173765161509875057L;

	/** Creates a new instance of InitializationException */
    public InitializationException() 
    {
        System.out.println("InitializationException");
    }
    
    /**
     * Constructor
     * @param s Message
     */
    public InitializationException(String s) 
    {
        System.out.println("InitializationException: "+s);
    }
    
    /**
     * 
     * @param rate
     * @param minimum
     * @param maximum 
     */
    public InitializationException(double rate,int minimum, int maximum)
    {
      System.out.println("Out of range exception caught "+ rate + " must be between "+ minimum +" and "+ maximum);
    }
  
    
    
}
