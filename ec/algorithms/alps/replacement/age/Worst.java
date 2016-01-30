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
 * @author anthony
 * 
 * makes tournament selectiion, use a percentage selection for worse individual replacement
 * else a random individual is selected out of the tournament individulas to be replaced
 *
 */
public class Worst  extends ALPSReplacement{

	private int individualID;
	
	public Worst() 
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
			nextGeneration.get(i).parentFlag = false; //unset parameter so that age will be incremented when individual is used as a parent
			
			if(nextGeneration.get(i).getAge() >= alpsLayers.layers.get(alpsLayers.index).getMaxAge() )
			{ 
				//fill higher layer with individuals that fall within its age limit
				if(higherPop.size() < alpsLayers.layers.get(alpsLayers.index+1).getParameters().getPopulationSize())
				{ 
					alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
					getCurrentPopulation().add(nextGeneration.get(i));
				}
				else //if(higherPop.size() > 0) //once higher layer is filled, do selective replacement based on new individuals that have higher age than in the individual in the  higher layer
				{
					this.individualID = worseFitIndividual(higherPop,nextGeneration.get(i));
					if(this.individualID < higherPop.size())
					{
						alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
						getCurrentPopulation().set(this.individualID,nextGeneration.get(i));
					}
				}
				//delete individuals that are older than their age and cant replace others in higher layers
				deleteList.add(nextGeneration.get(i)); 
			}
		  }
		  //remove all individuals older than current layer
		  for(int id=0;id<deleteList.size();id++)
			nextGeneration.remove(deleteList.get(id));
		/*
		System.out.println("DeleteList "+ deleteList.size()+ " Current1: "+nextGeneration.size()+ " Current2: "+ alpsLayers.layers.get(alpsLayers.index).getEvolution().
				getCurrentPopulation().size()+
				" Next: "+alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
				getCurrentPopulation().size()+" max age layer:  "+alpsLayers.layers.get(alpsLayers.index).getMaxAge()); //System.exit(0);
		*/
		}
		return nextGeneration;
	}
	
	
	

}
