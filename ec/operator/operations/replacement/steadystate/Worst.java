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
 * scans through higher layer and replaces worse individual
 * 
 * @author  Anthony Awuley
 */
public class Worst  extends AbstractSSReplacement {

	private int individualID = 0;
	
	public Worst() 
	{
		
	}

	@Override
	public String toString()
	{
		return "Worst Individual Replacement";
	}
	
    @Override
	public Population ssReplacements(Evolve e, ALPSLayers alpsLayers, Population currentPop, Population replacement) {
	
		SelectionOperation selectionOperation = new TournamentSelection();
		
		for(Individual ind: replacement.getAll()) //iterate through individuals to be replaced
		{
		   selectionOperation.performTournamentSelection(e,
				      alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize());
		    /*
		     * first half of elements in array are individuals from current layer
		     * keep padding list if the number of individuals in the current layer isn't
		     * up to the required population size. this is true from layer 1
		     */
		   if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  
				   (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() //NOT WORKING ALWAYS EQUAL
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
			{    //select worse individual from first half of currentPop
				this.individualID = worsePopulationIndividual(currentPop,
						alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size());
				//replace only if its better than worse individual
				if(ind.getFitness().getDouble() < currentPop.get(this.individualID).getFitness().getDouble())
				{
					//System.out.println("Replace "+currentPop.get(this.individualID).getFitness().getDouble() +" with: "+ind.getFitness().getDouble());
					currentPop.set(this.individualID, ind); 
				}
				
			}
		  
		}
		
		
		return currentPop;
	}
	


}
