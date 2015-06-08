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
package individuals.representation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Random;

import individuals.Chromosome;
import individuals.Gene;
import individuals.Representation;
import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;


/**
 *
 * @author anthony
 * @deprecated
 */
public class IntRep  extends Representation{
   
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
	     * 
	       */
	    public Gene clone() throws CloneNotSupportedException
	    {
	    	return (Gene) super.clone();
	    }

		
		public void generateChromosome(Chromosome ch,Properties prop)
		{
		   this.chromosome.clear();    
		   for(int i=0;i<ch.getChromosomeSize();i++)
		   {  
			 //this.chromosome.add(geneRepresentation(1,ch.getChromosomeSize()*14));//*14 to increase the range 
			 System.out.print(this.chromosome.get(i)+" ");    
		   } System.out.println();
			//set chromosome in base class
		    //added
		    this.rng = new MersenneTwisterFast();
			Collections.shuffle(this.chromosome, new Random(this.rng.nextLong()));
			
			ch.setGenes(this.chromosome);
	     }
	     
	   
    
 }
