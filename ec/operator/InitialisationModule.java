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
package ec.operator;

import ec.algorithms.ga.Evolve;
import ec.individuals.Gene;
import ec.individuals.populations.Population;

/**
 * Basic template for initilizing a population
 * 
 * @author Anthony Awuley
 *
 */
public interface InitialisationModule {

	public String toString(int popSize);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @return initialization for Generational Replacement
	 * @deprecated
	 */
	@Deprecated
	public  Population generateInitialPopulation(
			Evolve e, Gene g);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @return initialization for Generational Replacement
	 */
	public  Population generateInitialPopulation(
			Evolve e);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @param evaluations : number of evaluations performed so far
	 * @return initial population
	 * @deprecated
	 */
	@Deprecated
	public  Population generateInitialPopulation(
			Evolve e,
			Gene g,
			double evaluations);
    /**
     * 
     * @param e
     * @param evaluations
     * @return
     */
	Population generateInitialPopulation(Evolve e, double evaluations);
	
}
