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
package algorithms.aco;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import main.EC;
import util.Constants;

public class RunAnt implements EC {

	private int number_of_experiments;
	
	public RunAnt(Properties prop) throws IOException, InterruptedException, ExecutionException {
		
		/*
	     * SET SYSTEM PARAMETERS
	     */
	  	 this.number_of_experiments = Integer.parseInt(prop.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		  
		 for(int i=0;i<this.number_of_experiments;i++)
	     {
	         System.out.println("\nInitializing population for Run # "+i +"\n");
	         
	          //System.out.println("Using " + arguments[1]);
     	      long start = System.currentTimeMillis();
     	      
         	  AntColonyOptimisation antColonyOptimization = new AntColonyOptimisation(prop,i);
         	  
         	  double result = antColonyOptimization.start();
         	 //agentCompletionService.take();
         	 //antColonyOptimization.agentCompletionService.take();
         	 
  	         System.out.println("Took: " + (System.currentTimeMillis() - start) + " ms!");
  	         System.out.println("Result was: " + result + "\n\n");
  	       
  	         //antColonyOptimization.THREAD_POOL.shutdown();
  	         //antColonyOptimization.agentCompletionService.take();
	         
	     } 
	}

}
