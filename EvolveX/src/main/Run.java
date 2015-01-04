/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import algorithms.ga.Evolve;
import exceptions.InitializationException;
import util.Constants;

/**
 *
 * @author anthony
 */
public class Run extends Start{

    /**
     * @param args the command line arguments
     */
    
    //private long startTime;
     
    /** Creates a new instance of Run */
    public Run(String[] arguments) 
    {
    	
    	/* 
    	int port = 2502;
    	String host = "localhost";
    	//Server s = new Server(host,0,port);
    	//Client c = new Client(host,port);
    	
    	Server s = new Server(host,0,port);
    	Client c = new Client(host,port);
    	
    	 
    	s.startServer();
    	//GreetingServer.start(port);;
    	c.startClient();
    	
    	System.exit(0);
    	*/
        
        try 
        {
        	//propertiesFilePath = arguments[1].toString().length()>1?arguments[1]:Constants.DEFAULT_PROPERTIES;
            for(int f=1;f<arguments.length;f++)
            {  
            	/* Start Evolve time */
            	start = System.currentTimeMillis();
            	
            	propertiesFilePath = arguments[f].toString().length()>1?arguments[f]:Constants.DEFAULT_PROPERTIES;
            	new Evolve(this.setup());
            	
            	/* Start Evolve time */
            	end = System.currentTimeMillis();
            }
            
            /* Start Evolve time */
        	sysEndTime = System.currentTimeMillis();
            
		} 
        catch (InitializationException e) 
        {
			e.printStackTrace();
		}
        
    }
   
    
    
    public static void main(String[] args) 
    {
        new Run(args);
    }



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
}
