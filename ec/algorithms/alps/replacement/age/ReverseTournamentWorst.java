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
package ec.algorithms.alps.replacement.age;

import ec.operator.operations.SelectionOperation;
import ec.individuals.populations.Population;
import ec.algorithms.alps.ALPSReplacement;
import ec.algorithms.alps.system.ALPSLayers;
import ec.algorithms.ga.Evolve;

/**
 * 
 * makes tournament selectiion, use a percentage selection for worse individual replacement
 * else a random individual is selected out of the tournament individulas to be replaced
 *
 *@author Anthony Awuley
 */
public class ReverseTournamentWorst  extends ALPSReplacement{

	private int individualID;
	
	public ReverseTournamentWorst() 
	{
	}

	@Override
	public String toString()
	{
		return "Reverse Tournament Replacement";
	}
	
	@Override
	public Population performAgeLayerMovements(Evolve e, ALPSLayers alpsLayers,
			Population nextGeneration,SelectionOperation selectionOperation) {
		
		Population higherPop  = new Population();
		Population deleteList = new Population();
		
		if(alpsLayers.index < (alpsLayers.layers.size()-1) )
		{
	      //get population of next higher layer
		  higherPop = alpsLayers.layers.get(alpsLayers.index+1).
				getEvolution().getCurrentPopulation();
		
		  for(int i=0;i<nextGeneration.size();i++)
		  {
			if(nextGeneration.get(i).getAge() >= alpsLayers.layers.get(alpsLayers.index).getMaxAge())
			{ 
				//fill higher layer with individuals that fall withing its age limit
				if(higherPop.size() < alpsLayers.layers.get(alpsLayers.index+1).getParameters().getPopulationSize())
				{
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
					     getCurrentPopulation().add(nextGeneration.get(i));
				}
				else if(higherPop.size() > 0) //once higher layer is filled, do selective replacement based on new individuals that have higher age than in the individual in the  higher layer
				{
			        //perform tournament selection on higher layer
			        /* Wasn't working because of inconsistency in layer specification. e.g. this = index+1 
			         * function deals with index & index-1
			         * selectionOperation.performTournamentSelection(alpsLayers,higherPop.size(),
						   alpsLayers.layers.get(alpsLayers.index+1).getParameters().getTournamentSize());
					*/
					selectionOperation.performTournamentSelection(e, higherPop.size());
					this.individualID = worseTournamentIndividual(higherPop,selectionOperation.getTournamentSelection());
					
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().
					   set(this.individualID,nextGeneration.get(i));
					
				}
				deleteList.add(nextGeneration.get(i));
			 }
		  }
		  //remove all individuals older than current layer
		  for(int id=0;id<deleteList.size();id++)
			  nextGeneration.remove(deleteList.get(id));
		  
		  /*
		  System.out.println("DeleteList "+ deleteList.size()+ " -- Current!! "+nextGeneration.size()+
		  		" Next "+alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
		  		getCurrentPopulation().size()+" maxi age layer "+alpsLayers.layers.get(alpsLayers.index).getMaxAge()); //System.exit(0);
		  */
		}
		
		return nextGeneration;
	}
	
	
	

}
