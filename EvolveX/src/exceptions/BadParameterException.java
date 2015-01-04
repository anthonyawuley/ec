/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author anthony
 */
public class BadParameterException extends IllegalArgumentException {
    
    

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** Creates a new instance of BadParameterException */
    public BadParameterException() 
    {
      super("Parameter missing or invalid");
    }
    
    /**
     * Creates a new instance of BadParameterException
     * @param key parameter
     */
    public BadParameterException(String key) 
    {
        super("Parameter missing or invalid: "+key);
    }

    /**
     * Creates a new instance of BadParameterException
     * @param parameterName Parameter Name
     * @param className Class where parameter is being loaded from
     */
    public BadParameterException(String parameterName, String className) 
    {
      super("["+className+"] Parameter missing or invalid: "+parameterName);
    }
    

    
}
