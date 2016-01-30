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
package ec.individuals.representation;

import java.util.ArrayList;
import ec.individuals.Gene;
import ec.individuals.Representation;
import ec.util.random.RandomGenerator;



/**
 *
 * @author anthony
 */
public class VRPTW  extends Representation{
    
	   //private ArrayList<Integer> defaultChromeContainer = new ArrayList<>();
	

	  /*
	   public void generateChromosome(Chromosome ch,Properties prop) 
	   {
		  //limit repeated call to function, since its unecessary
		  if(this.chromosome.isEmpty())
		  {
			  setInitChrome(ch.getChromosomeSize());
		  }
		  //shuffle content of chromosome
		  //Collections.shuffle(this.chromosome);
		  
		  this.rng = new MersenneTwisterFast();
		  Collections.shuffle(this.chromosome, new Random(this.rng.nextLong()));
		  //Collections.shuffle(this.chromosome, new Random(System.currentTimeMillis()));
		  
	      ch.setChromosome(this.chromosome);
	    }
	
	   public void setInitChrome(int chromeSize)
	   {
		   this.chromosome.clear();
		   //begin count from 1, since 0 is used as depot
		   for(int i=1;i<=chromeSize;i++)
	       {  
	    	  this.chromosome.add(i); 
	       }
	   }
	 */
	   /**
	    * 
	    * @param min
	    * @param max
		* @return
		*/
		public static int geneRepresentation(int min, int max)
		{
		    return RandomGenerator.getMultiThreadedRandNumber(min,max);
		}

		
		public void sort() {
			// TODO Auto-generated method stub
		}

		
		public void addAll(ArrayList<Gene> immigrants) {
			// TODO Auto-generated method stub
		}

		
		public void clear() {
			// TODO Auto-generated method stub
		}
		
		 /**
	     * @throws CloneNotSupportedException 
	     * @deprecated
	     
	    public Gene clone() throws CloneNotSupportedException
	    {
	    	return (Gene) super.clone();
	    } 
	    */

	    
}
