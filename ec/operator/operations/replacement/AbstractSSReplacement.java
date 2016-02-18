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
package ec.operator.operations.replacement;

import java.util.ArrayList;

import ec.individuals.populations.Population;
import ec.operator.operations.SelectionOperation;
import ec.operator.operations.replacement.steadystate.ReverseTournamentWorst;
import ec.algorithms.alps.system.ALPSLayers;
import ec.algorithms.ga.Evolve;

/**
 * Defines a template for steady state replacement
 * 
 * @author Anthony Awuley
 *
 */
public abstract class AbstractSSReplacement {

	/**
	 * 
	 * @return
	 */
	@Override
	public abstract String toString();
	
	/**
	 * 
	 * @param alpsLayers
	 * @param currentPop
	 * @param current
	 * @return population with selected replacement individuals
	 */
	public abstract Population ssReplacements(
			Evolve e, SelectionOperation so,
			ALPSLayers alpsLayers,Population currentPop, Population replacement);
	
	
	
	/**
	 * Performs elitism by replacing best individuals using reverse tournament selection
	 * @param e
	 * @param so
	 * @param alpsLayers
	 * @param currentPop
	 * @param replacement
	 * @return
	 */
	public Population elitism(Evolve e, SelectionOperation so,
			ALPSLayers alpsLayers,Population currentPop, Population replacement)
	{
		return (new ReverseTournamentWorst()).
				ssReplacements(e, so, alpsLayers, currentPop, replacement);
	}
	
	
	/**
	 * only look through first half - since second half contains individuals of lower layer
	 * @param pop
	 * @return the worse individual in population
	 */
	protected int worsePopulationIndividual(Population pop,int firstHalf)
	{
		int worse = 0;
		for(int id=1;id<firstHalf;id++)
		{
			if(pop.get(worse).getFitness().getDouble() < pop.get(id).getFitness().getDouble() )
			{   
				worse = id;
			}
		}
		return worse;
	}
	
	/**
	 * tournament must be made only from first half
	 * @param pop
	 * @param tournament
	 * @return the worse tournament individual
	 */
	protected int worseTournamentIndividual(Population pop, ArrayList<Integer> tournament)
	{
		int worse = 0;
		for(int id=1;id<tournament.size();id++)
		{
			if(pop.get(tournament.get(worse)).getFitness().getDouble() < 
					pop.get(tournament.get(id)).getFitness().getDouble())
			{   
				worse = id;
			}
		}
		return tournament.get(worse);
	}
	
	
	/**
	 * 
	 * @param pop
	 * @param tournament
	 * @param age
	 * @return the nearest tournament individual
	 */
	protected int nearestTournamentIndividual(Population pop, ArrayList<Integer> tournament,double fitness)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<tournament.size();id++)
		{
			if(Math.abs(pop.get(tournament.get(nearest)).getFitness().getDouble() - fitness) > 
			Math.abs(pop.get(tournament.get(id)).getFitness().getDouble() - fitness))
			{   
				nearest = id;
			}
		}
		return tournament.get(nearest);
	}
	
	
	
	/**
	 * 
	 * @param pop
	 * @param age
	 * @return the nearest individual in population
	 */
	protected int nearestPopulationIndividual(Population pop,double fitness)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<pop.size();id++)
		{
			if(Math.abs(pop.get(nearest).getFitness().getDouble() - fitness) > 
			Math.abs(pop.get(id).getFitness().getDouble() - fitness))
			{   
				nearest = id;
			}
		}
		return nearest;
	}


}
