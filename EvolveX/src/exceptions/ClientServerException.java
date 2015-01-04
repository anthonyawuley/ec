/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package exceptions;


/**
 *
 * @author anthony
 */

public class ClientServerException extends Exception {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = -3173765161509875057L;

	/** Creates a new instance of InitializationException */
    public ClientServerException() 
    {
        System.out.println("Server-Client InitializationException \n "
        		+ "Please ensure that all three parameters(host,backlog,port) are provided for each host ");
    }
    
  
    
    
}
