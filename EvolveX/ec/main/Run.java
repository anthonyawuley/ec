/*******************************************************************************
 * Copyright (c) 2014, 2015 
 * Anthony Awuley - Brock University Computer Science Department
 * All rights reserved. This program and the accompanying materials
 * are made available under the Academic Free License version 3.0
 * which accompanies this distribution, and is available at
 * https://aawuley@bitbucket.org/aawuley/evolvex.git
 *
 * Contributors:
 *     ECJ                     MersenneTwister & MersenneTwisterFast (https://cs.gmu.edu/~eclab/projects/ecj)
 *     voidException      Tabu Search (http://voidException.weebly.com)
 *     Lucia Blondel       Simulated Anealing 
 *     
 *
 *        
 *******************************************************************************/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import parameter.Parameters;
import exceptions.InitializationException;
import util.Constants;

/**
 *
 * @author anthony
 */
public class Run extends Parameters{

	  private long start      = 0;
	  private long end        = 0;
	  private long sysEndTime = 0;
	  
		
	  long sysStartTime = System.currentTimeMillis();
	  private EC ec;
	
	  @Override
	  public String toString() {
			return "Begin evolution";
	  }   
    
    //private long startTime;
     
    /** Creates a new instance of Run */
    public Run(String[] arguments) 
    {
    	/* Start Evolve time */
    	start = System.currentTimeMillis();
    	
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
            	propertiesFilePath = arguments[f].toString().length()>1?arguments[f]:Constants.DEFAULT_PROPERTIES;
            	//new Evolve(this.setup());
            	ec = mainClass(this.setup());
            }
            /* Start Evolve time */
        	sysEndTime = System.currentTimeMillis();
		} 
        catch (InitializationException e) 
        {
			e.printStackTrace();
		}
        
        /* Start Evolve time */
    	end = System.currentTimeMillis();
        
    }
   
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        new Run(args);
    }



	
}
