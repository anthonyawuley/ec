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
package ec.algorithms.tabu;

import java.io.IOException;
import java.util.Properties;

import ec.main.EC;
import ec.algorithms.tabu.solution.Writer;
import ec.util.Constants;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class RunTabu  implements EC {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** */
	int number_of_experiments;
	
	public RunTabu(){}
	
	@Override
	public void start(Properties prop) throws IOException 
	{
		
		 this.number_of_experiments = Integer.parseInt(prop.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		  
		 for(int i=0;i<this.number_of_experiments;i++)
	     {
	         System.out.println("\nInitializing population for Run # "+i +"\n");
	         
	          //System.out.println("Using " + arguments[1]);
     	      long start = System.currentTimeMillis();
     	      
     	      TabuSearch tabu = new TabuSearch(prop,i);
         	  
         	  int[] result = tabu.start();
         	 //agentCompletionService.take();
         	 //antColonyOptimization.agentCompletionService.take();
         	 
  	         System.out.println("Took: " + (System.currentTimeMillis() - start) + " ms!");
  	         //System.out.println("Result was: " + result + "\n\n");
  	         Writer.printSolution(result);
  	         //antColonyOptimization.THREAD_POOL.shutdown();
  	         //antColonyOptimization.agentCompletionService.take();
	         
	     }
		
	}

}
