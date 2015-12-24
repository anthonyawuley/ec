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
package operator.operations.replacement;

import java.util.ArrayList;

import individuals.populations.Population;
import algorithms.alps.system.ALPSLayers;
import algorithms.ga.Evolve;

public abstract class AbstractSSReplacement {

	/**
	 * 
	 * @return
	 */
	public abstract String toString();
	
	/**
	 * 
	 * @param alpsLayers
	 * @param currentPop
	 * @param current
	 * @return population with selected replacement individuals
	 */
	public abstract Population ssReplacements(Evolve e,
			ALPSLayers alpsLayers,Population currentPop, Population replacement);
	
	
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
			}//System.out.print(" "+pop.get(tournament.get(id)).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(tournament.get(nearest)).getAge()+" \n");
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
			}//System.out.print(" "+pop.get(id).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(nearest).getAge()+" \n");
		return nearest;
	}


}
