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
 * TSP representation
 * @author Anthony Awuley
 */
public class TSP  extends Representation{
    
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

		/**
		 * 
		 */
		public void sort() {
			// TODO Auto-generated method stub
		}

	    /**
	     * 
	     * @param immigrants
	     */
		public void addAll(ArrayList<Gene> immigrants) {
			// TODO Auto-generated method stub
		}

		/**
		 * 
		 */
		public void clear() {
			// TODO Auto-generated method stub
		}
	
	    
}
