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
package ec.main;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import ec.parameter.Parameters;
import ec.exceptions.InitializationException;
import ec.util.Constants;

/**
 * Main method is in this class and is the highest class for evolution.
 * Initializations of the EA class specified through main-class parameter in teh parameter
 * file is done here and parameter file is also read and initialized
 * 
 * @author Anthony Awuley
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
     
    /** 
     * Creates a new instance of Run 
     * @throws ExecutionException 
     * @throws InterruptedException 
     * @throws IOException 
     */
    public Run(String[] arguments) throws IOException, InterruptedException, ExecutionException 
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
        	
            for(int f=1;f<arguments.length;f++)
            {  
            	/* Start Evolve time */
           	    start = System.currentTimeMillis();
           	 
            	propertiesFilePath = arguments[f].toString().length()>1?arguments[f]:Constants.DEFAULT_PROPERTIES;
                /* load main class */	
            	ec = mainClass(this.setup());
            	/* begin evolution */
            	ec.start(properties);

              /* End Evolve time */
               end = System.currentTimeMillis();
            }
            /* Start Evolve time */
       	   sysEndTime = System.currentTimeMillis();
       	   
           System.out.println("Exiting!!");
           System.exit(0);
		} 
        catch (InitializationException e) 
        {
        	System.out.println("Failed to initialize...\nCheck main-class parameter in the parameter file");
			e.printStackTrace();
		}
        
        
    }
    
    /**
     * Begin evolution and set up parameters specified in standard input
     * multiple parameter files can be spefified in the argument list
     * e.g. -file parameter-file-1.params parameter-file-2.params parameter-file-3.params ...
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try {
			new Run(args);
		} catch (IOException | InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
    }
}
