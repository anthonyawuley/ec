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
package ec.individuals.representation.hybrid;

import java.util.ArrayList;

import ec.algorithms.ga.Evolve;
import ec.individuals.Gene;
import ec.individuals.Representation;
import ec.util.random.RandomGenerator;



/**
 *
 * @author anthony
 * note chromosome is started from index 2
 * this was because index one is used as depot
 */
public class TSP  extends Representation {
    
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
		  this.rng = new MersenneTwisterFast();
		  Collections.shuffle(this.chromosome, new Random(this.rng.nextLong()));
	      ch.setChromosome(this.chromosome);
	    }
	
	   //NOTE: the start of chromosome from index 2
	   public void setInitChrome(int chromeSize)
	   {
		   this.chromosome.clear();
		   //begin count from 1, since 0 is used as depot
		   for(int i=2;i<=chromeSize;i++)
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
		public static int geneRepresentation(Evolve e,int min, int max)
		{
		    return RandomGenerator.getMultiThreadedRandNumber(e,min,max);
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
	     * 
	     */
	    @Override
		public Gene clone() throws CloneNotSupportedException
	    {
	    	return (Gene) super.clone();
	    }

	    
}
