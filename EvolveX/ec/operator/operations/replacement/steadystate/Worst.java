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
package operator.operations.replacement.steadystate;

import individuals.Individual;
import individuals.populations.Population;
import operator.operations.SelectionOperation;
import operator.operations.replacement.AbstractSSReplacement;
import operator.operations.selection.TournamentSelection;
import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;
import algorithms.alps.system.ALPSLayers;

/**
 * 
 * @author anthony
 *
 * scans through higher layer and replaces any first encountered worse individual
 */
public class Worst  extends AbstractSSReplacement {

	private int individualID = 0;
	
	public Worst() 
	{
		
	}

	
	
	public String toString()
	{
		return "Worst Individual Replacement";
	}
	
	
	
    public Population ssReplacements(ALPSLayers alpsLayers, Population currentPop, Population replacement) {
		
		//Population currentPop = null;
		//Population deleteList = new Population();
		SelectionOperation selectionOperation = new TournamentSelection();
		
		//Do not clone
		//currentPop = (Population) alpsLayers.layers.get(alpsLayers.index).
		//		getEvolution().getCurrentPopulation();
		
		@SuppressWarnings("unused")
		RandomGenerator randGen = new RandomGenerator(); 
		MersenneTwisterFast mtf = new MersenneTwisterFast();
		mtf.setSeed(alpsLayers.layers.get(alpsLayers.index).getParameters().getSeed()); //set seed
		
		for(Individual ind: replacement.getAll()) //iterate through individuals to be replaced
		{
		   selectionOperation.performTournamentSelection(
				      alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize(),
				      alpsLayers.layers.get(alpsLayers.index).getParameters().getTournamentSize());
		    /**
		     * first half of elements in array are individuals from current layer
		     * keep padding list if the number of individuals in the current layer isn't
		     * up to the required population size. this is true from layer 1
		     */
		   if(alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  
				   (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() //NOT WORKING ALWAYS EQUAL
					< (alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize())) )
			{   //System.out.println("Bottom Padding"+currentPop.size());
				currentPop.add(0,ind); //System.out.println("Population size()"+currentPop.size());
			}
		   else if( !alpsLayers.layers.get(alpsLayers.index).getIsBottomLayer() &&  (alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size() 
					< (alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize())) 
					//&& currentPop.size()<(2*alpsLayers.layers.get(alpsLayers.index).getParameters().getPopulationSize()) 
					)
			{   //System.out.println("Upper Padding"+alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size());
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
		
		//System.out.println("--"+currentPop.size()+" ---"+alpsLayers.layers.get(alpsLayers.index).getEvolution().getCurrentPopulation().size());
		/*
		System.out.println(deleteList.size()+ " -- Current!! "+currentPop.size()+
				" Next "+alpsLayers.layers.get(alpsLayers.index).getEvolution().
				getCurrentPopulation().size()); //System.exit(0);
		*/
		return currentPop;
	}
	


}
