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
package algorithms.simulatedannealing;

import java.io.IOException;
import java.util.Properties;

import main.EC;
import util.Constants;

public class RunSA implements EC {

	int number_of_experiments;
	boolean isTSP = Boolean.FALSE;
	
	public RunSA(Properties prop) throws IOException 
	{
		
		 this.number_of_experiments = Integer.parseInt(prop.getProperty(Constants.NUMBER_OF_EXPERIMENTS));
		 this.isTSP                 = Boolean.parseBoolean(prop.getProperty(Constants.IF_TSP_TRUE_IF_VRP_FALSE));
		  
		 for(int i=0;i<this.number_of_experiments;i++)
	     {   
	         System.out.println("\nInitializing population for Run # "+i +"\n");
	  
     	     long start = System.currentTimeMillis();
     	      
	         if(this.isTSP) //set a flag to determine if its VRP or TSP
	         {
	        	 new StartTSP(prop,i).start();
	         }
	         else
	         {   
	        	 System.out.println("Starting Capacitated Vehicle Routing Problem...");
	        	 new StartCVRP(prop,i); 
	         }
	         
	         long end = System.nanoTime();
  	         //System.out.println("Took: " + (System.currentTimeMillis() - start) + " ms!");
  	         System.out.println("Took: " + Math.floor((end-start) * Math.pow(10, -9)) + " ms!");
	         
	     } 
		
	}

}
