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
package ec.operator.operations.replacement.steadystate;

import ec.individuals.Individual;
import ec.individuals.populations.Population;
import ec.operator.operations.SelectionOperation;
import ec.operator.operations.replacement.AbstractSSReplacement;
import ec.operator.operations.selection.TournamentSelection;
import ec.algorithms.alps.system.ALPSLayers;
import ec.algorithms.ga.Evolve;

/**
 * 
 * @author Anthony Awuley
 *
 */
public class Nearest extends AbstractSSReplacement{

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
	public Population ssReplacements(Evolve e, SelectionOperation so, ALPSLayers alpsLayers, Population currentPop, Population replacement) {
			
			//Do not clone
			//currentPop = (Population) alpsLayers.layers.get(alpsLayers.index).
			//		getEvolution().getCurrentPopulation();
			
		
			for(Individual ind: replacement.getAll()) //iterate through individuals to be replaced
			{
			   so.performTournamentSelection(e,
					      alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize());
			   
				 /*
				  selectionOperation.performTournamentSelection(
						  currentPop.size(),
					      alpsLayers.layers.get(alpsLayers.index).getParameters().getTournamentSize());
			     */  
			    /**
			     * first half of elements in array are individuals from current layer
			     * keep padding list if the number of individuals in the current layer isn't
			     * up to the required population size. this is true from layer 1
			     */
			   if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() //NOT WORKING ALWAYS EQUAL
						< (alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize())) )
				{   
					currentPop.add(0,ind); //System.out.println("Population size()"+currentPop.size());
				}
			   else if( !alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() 
						< (alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize())) 
						//&& currentPop.size()<(2*alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize()) 
						)
				{  
					currentPop.add(0,ind); //System.out.println("Population size()"+currentPop.size());
				}
				else
				{  
			        //select nearest individual in population
					this.individualID = nearestPopulationIndividual(currentPop,ind.getFitness().getDouble());
				
					//System.out.println("Replace "+currentPop.get(this.individualID).getFitness().getDouble() +" with: "+ind.getFitness().getDouble());
					 currentPop.set(this.individualID, ind); 
				}
			  
			}
			
			//System.out.println("--"+currentPop.size()+" ---"+alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size());
			/*
			System.out.println(deleteList.size()+ " -- Current!! "+currentPop.size()+
					" Next "+alpsLayers.layers.get(alpsLayers.index).getEvolution().
					getCurrentPopulation().size()); //System.exit(0);
			*/
			return currentPop;
		}


}
