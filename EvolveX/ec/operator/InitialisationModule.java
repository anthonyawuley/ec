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
package operator;

import java.util.Properties;

import algorithms.ga.Evolve;
import individuals.Gene;
import individuals.populations.Population;

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
	public  Population generateInitialPopulation(
			Gene g,
			Properties prop, 
			int populationSize, 
			int chromosomeLength);
	
	/**
	 * 
	 * @param g
	 * @param prop
	 * @param populationSize
	 * @param chromosomeLength
	 * @return initialization for Generational Replacement
	 */
	public  Population generateInitialPopulation(
			Properties prop, 
			int populationSize, 
			int chromosomeLength);
	
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
	public  Population generateInitialPopulation(
			Gene g,
			Properties prop, 
			int populationSize, 
			int chromosomeLength,
			double evaluations);

	Population generateInitialPopulation(Properties prop, int populationSize,
			int chromosomeLength, double evaluations);
	
}
