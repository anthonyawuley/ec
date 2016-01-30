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
package ec.util.statistics;

import ec.individuals.fitnesspackage.PopulationFitness;
import ec.individuals.populations.Population;

import java.util.ArrayList;
import java.util.Properties;

import ec.algorithms.alps.layers.Layer;

public interface StatisticsCollector {

	/**
	 * 
	 * @param pop
	 * @param p
	 * @param bestIndividuals
	 * @param run
	 * @param generation
	 * @param f
	 */
	 public  void printStatsReport(
	    		Population pop,
	    		Properties p, 
	    		ArrayList<Integer> bestIndividuals,
	    		final int run,
	    		final int generation, 
	    		PopulationFitness f);
	 
	 /**
	  * OVER LOADED FOR ALPS
	  * @param pop
	  * @param p
	  * @param bestIndividuals
	  * @param layer
	  * @param generation
	  * @param f
	  */
	 public  void printStatsReport(
	    		Population pop,
	    		Properties p, 
	    		ArrayList<Integer> bestIndividuals,
	    		final Layer layer,
	    		final int generation, 
	    		final int run,
	    		PopulationFitness f);
	
	
}
