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

import java.util.ArrayList;

import operator.operations.SelectionOperation;
import operator.operations.selection.TournamentSelection;
import util.random.MersenneTwisterFast;
import util.random.RandomGenerator;
import individuals.populations.Population;
import algorithms.alps.ALPSReplacement;
import algorithms.alps.system.ALPSLayers;
import algorithms.ga.Evolve;

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
	public Population performAgeLayerMovements(Evolve e, ALPSLayers alpsLayers,
			Population current) {
		
		Population higherPop = null;
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
					selectionOperation.performTournamentSelection(e,alpsLayers);
			        
					if(mtf.nextDouble()<= alpsLayers.layers.get(alpsLayers.index).getParameters().getLayerSelectionPressure())
					{ // n% worse replacement  : NB: index returned by nearestTournamentIndividual is a value in  getTournamentSelection()
					   this.individualID = nearestTournamentIndividual(
									               higherPop,
									               selectionOperation.getTournamentSelection(),
									               current.get(i).getAge());
					}
					else
					{ //(100-n)% random replacement
						this.individualID = selectionOperation.performTournamentSelection(e,
								selectionOperation.getTournamentSelection().size()).get(0);
					}
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
	 * 
	 * @param pop
	 * @param tournament
	 * @param age
	 * @return the nearest tournament individual
	 */
	private int nearestTournamentIndividual(Population pop, ArrayList<Integer> tournament,double age)
	{
		int nearest = 0;
		//System.out.println("\nBegin:::\n" + age);
		for(int id=1;id<tournament.size();id++)
		{
			if(Math.abs(pop.get(tournament.get(nearest)).getAge() - age) > Math.abs(pop.get(tournament.get(id)).getAge() - age))
			{   
				nearest = id;
			}//System.out.print(" "+pop.get(tournament.get(id)).getAge()+" ");
		}
		//System.out.println("\nThis is the nearest "+pop.get(tournament.get(nearest)).getAge()+" \n");
		return tournament.get(nearest);
	}
	


	

}
