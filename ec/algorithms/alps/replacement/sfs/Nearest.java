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
package ec.algorithms.alps.replacement.sfs;

import ec.operator.operations.SelectionOperation;
import ec.util.random.MersenneTwisterFast;
import ec.util.random.RandomGenerator;
import ec.individuals.populations.Population;
import ec.algorithms.alps.ALPSReplacement;
import ec.algorithms.alps.system.ALPSLayers;
import ec.algorithms.ga.Evolve;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class Nearest extends ALPSReplacement{

	private int individualID;
	
	public Nearest() 
	{
	}
	
	@Override
	public String toString()
	{
		return "Nearest Neighbour Replacment";
	}


	@Override
	public Population performAgeLayerMovements(Evolve e, ALPSLayers alpsLayers,
			Population nextGeneration,SelectionOperation selectionOperation) 
	{
		
		Population higherPop = null;
		Population deleteList = new Population();
		
		
		higherPop = alpsLayers.layers.get(alpsLayers.index+1).
					getEvolution().getCurrentPopulation().clone();
	
		
		for(int i=0;i<nextGeneration.size();i++)
		{
			if(nextGeneration.get(i).getAge() >= alpsLayers.layers.get(alpsLayers.index).getMaxAge() )
			{ 
				//fill higher layer with individuals that fall withing its age limit
				if(higherPop.size() < alpsLayers.layers.get(alpsLayers.index+1).getParameters().getPopulationSize())
				{
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
					getCurrentPopulation().add(nextGeneration.get(i));
				}
				else if(higherPop.size()>0) //once higher layer is filled, do selective replacement based on new individuals that have higher age than in the individual in the  higher layer
				{
					@SuppressWarnings("unused")
					RandomGenerator randGen = new RandomGenerator(); 
			        MersenneTwisterFast mtf = new MersenneTwisterFast();
			        mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
			        
			        this.individualID = nearestPopulationIndividual( //select worst individual in population
									               higherPop,
									               nextGeneration.get(i).getAge());
					
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().
					   set(this.individualID,nextGeneration.get(i));
					deleteList.add(nextGeneration.get(i));
				}
			}
		}
		//remove all individuals older than current layer
		for(int id=0;id<deleteList.size();id++)
		{
			//nextGeneration.remove(deleteList.get(id));
		}
		
		System.out.println(deleteList.size()+ " -- Current!! "+nextGeneration.size()+
				" Next "+alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
				getCurrentPopulation().size()); //System.exit(0);
		
		return nextGeneration;
	}
	

	/**
	 * 
	 * @param pop
	 * @param age
	 * @return the nearest individual in population
	 */
	private int nearestPopulationIndividual(Population pop,double age)
	{
		int nearest = 0;
		System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<pop.size();id++)
			if(Math.abs(pop.get(nearest).getAge() - age) > Math.abs(pop.get(id).getAge() - age))
				nearest = id;
		return nearest;
	}
	

}
