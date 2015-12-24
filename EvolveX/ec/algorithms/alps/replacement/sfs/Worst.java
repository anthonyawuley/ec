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
package algorithms.alps.replacement.sfs;

import individuals.populations.Population;
import algorithms.alps.ALPSReplacement;
import algorithms.alps.system.ALPSLayers;
import algorithms.ga.Evolve;

/**
 * 
 * @author anthony
 *
 * scans through higher layer and replaces any first encountered worse individual
 */
public class Worst  extends ALPSReplacement{

	private int individualID = 0;
	
	public Worst() 
	{
		
	}

	
	
	public String toString()
	{
		return "Worst Individual Replacement";
	}
	
	@Override
	public Population performAgeLayerMovements(Evolve e, ALPSLayers alpsLayers,
			Population current) {
		
		Population higherPop = null;
		Population deleteList = new Population();
		
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
				{ /*
				  //note higherPop.get(0) has not been checked in individualWithSmallestAge
				  if(worseBottomIndividual(higherPop,current.get(i).getAge()))
				  {   //add individual to immediate higher layer
					  alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().
					  set(this.individualID,current.get(i));
					  deleteList.add(current.get(i));
				  }*/
				  
				  /* System.out.println("individual id "+ this.individualID + " with age "+ 
				       alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().get(this.individualID).getAge()
				  		+ "is to be replaced by " + current.get(i).getAge()); //System.exit(0);
		          */
				  /*
				   * comment next 4 lines when using worseBottomIndividual
				   */
				  this.individualID = worsePopulationIndividual(higherPop);
				  alpsLayers.layers.get(alpsLayers.index+1).getEvolution().getCurrentPopulation().
				  set(this.individualID,current.get(i));
				  deleteList.add(current.get(i));
				  
				  
				}
			}
		}
		//remove all individuals older than current layer
		for(int id=0;id<deleteList.size();id++)
		{
			//current.remove(deleteList.get(id));
		}
		
		System.out.println(deleteList.size()+ " -- Current!! "+current.size()+
				" Next "+alpsLayers.layers.get(alpsLayers.index+1).getEvolution().
				getCurrentPopulation().size()); //System.exit(0);
		
		return current;
	}
	
	/**
	 * @param pop
	 * @param currentIndAge
	 * @return returns first encountered worse age
	 */
	@SuppressWarnings("unused")
	private boolean worseBottomIndividual(Population pop,int currentIndAge)
	{
		
		for(int j=0;j<pop.size();j++)
		{
		  if(pop.get(j).getAge() < currentIndAge)
		  { //System.out.println("\nindividual age "+pop.get(j).getAge() + " current age "+currentIndAge +" id "+ j);
			this.individualID = j;
			return Boolean.TRUE;
		  }
		}
	  return Boolean.FALSE; 
	}
	

	

}
