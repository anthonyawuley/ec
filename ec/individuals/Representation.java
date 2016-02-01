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
package ec.individuals;

import java.util.ArrayList;
import java.util.Properties;

import ec.util.random.MersenneTwisterFast;
import ec.individuals.representation.*;


/**
 * Specifies valrius chromosome representations (e.g. int, char, double, long etc)
 * Not usefull after introduction of a datastructure for a node (see Gene class)
 * 
 * @deprecated
 * @author anthony
 */
public abstract class Representation{
    
	 protected ArrayList<Gene> chromosome = new ArrayList<>();
	 protected MersenneTwisterFast rng;
	
	 
	 /**
	  * 
	  * @param ch
	  * @param p
	  * @param rng
	  */
	 public void createChromosome(Chromosome ch,Properties p, MersenneTwisterFast rng) 
	 {}
	 
	 
	/**
     * 
     * @param min
     * @param max
     * @return 
     * @deprecated
     */
    @Deprecated
	public int integerGene (int min, int max)
    {
    	return IntRep.geneRepresentation(min, max);
    }
    
    /**
     * 
     * @param min
     * @param max
     * @return 
     * @deprecated
     */
    @Deprecated
	public int characterGene (int min, int max)
    {
    	return CharRep.geneRepresentation(min, max);
    }
    
    /**
     * 
     * @param min
     * @param max
     * @return 
     * @deprecated
     */
    @Deprecated
	public int vrptwGene (int min, int max)
    {
    	return VRPTW.geneRepresentation(min, max);
    }
	/**
     * ant and tabu
	 * @param trim
	 * @return
	 */
	 protected final String[] sweepNumbers(String trim) 
	 {
	    String[] arr = new String[3];
	    int currentIndex = 0;
	    
	    for (int i = 0; i < trim.length(); i++) 
	    {
	      final char c = trim.charAt(i);
	      if ((c) != 32) 
	      {
	        for (int f = i + 1; f < trim.length(); f++) 
	        {
	          final char x = trim.charAt(f);
	          if ((x) == 32) 
	          {
	            arr[currentIndex] = trim.substring(i, f);
	            currentIndex++;
	            break;
	          } 
	          else if (f == trim.length() - 1) 
	          {
	            arr[currentIndex] = trim.substring(i, trim.length());
	            break;
	          }
	        }
	        i = i + arr[currentIndex - 1].length();
	      }
	    }
	    return arr;
	  }
    
}
