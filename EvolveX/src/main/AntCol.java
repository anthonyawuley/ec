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
import algorithms.aco.RunAnt;

public class AntCol extends Start{

	public AntCol() {
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public String toString() 
	{
		 return "Ant Colony Optimization";
	}
	
   
 /** 
  * Creates a new instance of Run 
  * @throws IOException 
  * @throws ExecutionException 
  * @throws InterruptedException 
  */
   public AntCol(String[] arguments) throws IOException, InterruptedException, ExecutionException 
   {
       try 
       {
           for(int f=1;f<arguments.length;f++)
           {  
        	   /* Start Evolve time */
             	start = System.currentTimeMillis();
           	
           	  propertiesFilePath = arguments[f].toString().length()>1?arguments[f]:Constants.DEFAULT_PROPERTIES;
           	  //AntColonyOptimisation antColonyOptimization = new AntColonyOptimisation(arguments[1],this.setup());
           	  new RunAnt(this.setup());
           	  
           	 /* End Evolve time */
          	  end = System.currentTimeMillis();
           } 
           /* End time */
         	sysEndTime = System.currentTimeMillis();
           System.out.println("Exiting!!");
           System.exit(0);
		} 
       catch (InitializationException e) 
       {
			e.printStackTrace();
	   }
       
   }
  
   
   
   public static void main(String[] args) throws IOException, InterruptedException, ExecutionException 
   {
       //TODO code application logic here
       new AntCol(args);
     
   }
   
   
}
