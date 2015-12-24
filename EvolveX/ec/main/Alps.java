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
package main;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import util.Constants;
import exceptions.InitializationException;
import algorithms.alps.system.Engine;

/**
 * This class starts the main ALPS evolutionary strategy for GA.
 * @author anthony
 *
 */
public class Alps extends Start{

	
	public Alps() 
	{}

	
	@Override
	public String toString() 
	{
		 return "Age Layered Population Strategy";
	}
	
   
 /** 
  * @author anthony
  * Creates a new instance of Run 
  * @throws IOException 
  * @throws ExecutionException 
  * @throws InterruptedException 
  */
   public Alps(String[] arguments) throws IOException, InterruptedException, ExecutionException 
   {
       try 
       {
          for(int f=1;f<arguments.length;f++)
          {  
        	  /* Start Evolve time */
          	  start = System.currentTimeMillis();
          	
          	  propertiesFilePath = arguments[f].toString().length()>1?
          			  arguments[f]:Constants.DEFAULT_PROPERTIES;
           	  new Engine(this.setup());
           	  
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
		  e.printStackTrace();
	   }
   }
  
   
   /**
    * @author anthony
    * @param args
    * @throws IOException
    * @throws InterruptedException
    * @throws ExecutionException
    */
   public static void main(String[] args) 
		   throws IOException, InterruptedException, ExecutionException 
   {
       new Alps(args);
   }
   
   
}
