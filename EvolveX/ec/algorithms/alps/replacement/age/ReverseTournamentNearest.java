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
package algorithms.alps.replacement.age;

import operator.operations.SelectionOperation;
import operator.operations.selection.TournamentSelection;
import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;
import individuals.populations.Population;
import algorithms.alps.ALPSReplacement;
import algorithms.alps.system.ALPSLayers;

public class ReverseTournamentNearest extends ALPSReplacement{

	private int individualID;
	
	public ReverseTournamentNearest() 
	{
	}
	
	public String toString()
	{
		return "Nearest Neighbour Replacment";
	}


	@Override
	public Population performAgeLayerMovements(ALPSLayers alpsLayers,
			Population current) {
		
		Population higherPop  = new Population();
		Population deleteList = new Population();
		SelectionOperation selectionOperation = new TournamentSelection();
		
		
		higherPop = (Population) alpsLayers.layers.get(alpsLayers.index+1).
				getEvolution().getCurrentPopulation().clone();
		
		for(int i=0;i<current.size();i++)
		{
			if(current.get(i).getAge() >= alpsLayers.layers.get(alpsLayers.index).getMaxAge() )
			{ 
				//fill higher layer with individuals that fall withing its age limit
				if(higherPop.size() < alpsLayers.layers.get(alpsLayers.index+1).getParameters().getPopulationSize())
				{
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
					getCurrentPopulation().add(current.get(i));
				}
				else if(higherPop.size()>0) //once higher layer is filled, do selective replacement based on new individuals that have higher age than in the individual in the  higher layer
				{
					@SuppressWarnings("unused")
					RandomGenerator randGen = new RandomGenerator(); 
			        MersenneTwisterFast mtf = new MersenneTwisterFast();
			        mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
			        
			        //perform tournament selection on higher layer
					selectionOperation.performTournamentSelection(alpsLayers,higherPop.size(),
						 alpsLayers.layers.get(alpsLayers.index+1).getParameters().getTournamentSize());
					
					this.individualID = nearestTournamentIndividualFitness(
				               higherPop,
				               selectionOperation.getTournamentSelection(),
				               current.get(i).getFitness().getDouble());
					/*
					if(mtf.nextDouble()<= alpsLayers.layers.get(alpsLayers.index).getParameters().getLayerSelectionPressure())
					{ // n% worse replacement  : NB: index returned by nearestTournamentIndividual is a value in  getTournamentSelection()
					   this.individualID = nearestTournamentIndividualFitness(
									               higherPop,
									               selectionOperation.getTournamentSelection(),
									               current.get(i).getFitness().getDouble());
					}
					else
					{ //(100-n)% random replacement
						this.individualID = selectionOperation.performTournamentSelection(
								selectionOperation.getTournamentSelection().size(),1).get(0);
					}
					*/
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().
					   set(this.individualID,current.get(i));
				}
				//delete individuals that are older than their age and cant replace others in higher layers
				deleteList.add(current.get(i)); 
			}
		}
		//remove all individuals older than current layer
		for(int id=0;id<deleteList.size();id++)
		{
			current.remove(deleteList.get(id));
		}
		
		System.out.println(deleteList.size()+ " -- Current!! "+current.size()+
				" Next "+alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
				getCurrentPopulation().size()); //System.exit(0);
		
		return current;
	}
	

	


	

}
